/*
 * Gestor de pistes d'esquí
 * Gestiona les operacions CRUD sobre la taula 'pistes' de PostgreSQL
 */
package gestors;

import model.Pista;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de pistes que proporciona operacions CRUD sobre la taula 'pistes'
 * @author joan
 */
public class GestorPista {

    Connection con;

    public GestorPista(Connection con) {
        this.con = con;
    }

    /**
     * Esborra tot el contingut de la taula pista
     *
     * @throws GestorExcepcio en cas que no es pugui esborrar
     */
    public void esborraTot() throws GestorExcepcio {
        String sql = "DELETE FROM pista";
        // Step 1: Execute a global delete statement on the table
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new GestorExcepcio("Error while deleting all records: " + e.getMessage());
        }
    }

    /**
     * Afegeix una pista a la base de dades
     *
     * @param pista una pista
     * @throws GestorExcepcio en cas que no es pugui afegir
     */
    public void afegeixPista(Pista pista) throws GestorExcepcio {
        // Step 1: Prepare SQL with composite types (casting) and array fields
        String sql = "INSERT INTO pista (id, nom, color, oberta, especificacions, condicions, remuntadors_acces) "
                   + "VALUES (?, ?, ?, ?, (?,?,?)::dades_tecniques, (?,?)::estat_neu, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Step 2: Set basic parameters
            ps.setString(1, pista.getId());
            ps.setString(2, pista.getNom());
            ps.setString(3, pista.getColor());
            ps.setBoolean(4, pista.isOberta());
            
            // Step 3: Set technical specifications (composite type)
            ps.setInt(5, pista.getLongitud());
            ps.setInt(6, pista.getDesnivell());
            ps.setInt(7, pista.getPendentMax());
            
            // Step 4: Set snow conditions (composite type)
            ps.setInt(8, pista.getGruix());
            ps.setString(9, pista.getQualitat());
            
            // Step 5: Map Java List to PostgreSQL Array
            String[] liftsArray = pista.getRemuntadorsAcces().toArray(new String[0]);
            Array sqlArray = con.createArrayOf("varchar", liftsArray);
            ps.setArray(10, sqlArray);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new GestorExcepcio("Error while adding the pista: " + e.getMessage());
        }
    }

    /**
     * Obté una pista pel seu identificador
     *
     * @param id l'identificador d'una pista
     * @return la pista
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir
     */
    public Pista obtePista(String id) throws GestorExcepcio {
        Pista pista = null;
        // Step 1: Use specific SQL syntax to extract internal fields from composite types
        String sql = "SELECT id, nom, color, oberta, " +
                     "(especificacions).longitud_m, (especificacions).desnivell_m, (especificacions).pendent_max_pct, " +
                     "(condicions).gruix_cm, (condicions).qualitat, remuntadors_acces " +
                     "FROM pista WHERE id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Step 2: Map the SQL array back to a List<String>
                List<String> lifts = new ArrayList<>();
                Array arr = rs.getArray("remuntadors_acces");
                if (arr != null) {
                    String[] data = (String[]) arr.getArray();
                    for (String s : data) lifts.add(s);
                }

                // Step 3: Instantiate Pista with full data for correct equals() comparison
                pista = new Pista(
                    rs.getString("id"),
                    rs.getString("nom"),
                    rs.getString("color"),
                    rs.getBoolean("oberta"),
                    rs.getInt("longitud_m"),
                    rs.getInt("desnivell_m"),
                    rs.getInt("pendent_max_pct"),
                    rs.getInt("gruix_cm"),
                    rs.getString("qualitat"),
                    lifts
                );
            } else {
                // Step 4: Throw exception if ID is not found (required by JUnit tests)
                throw new GestorExcepcio("No pista found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new GestorExcepcio("Error while retrieving the pista: " + e.getMessage());
        }
        return pista;
    }

    /**
     * Esborra una pista per id
     *
     * @param id l'identificador de la pista
     * @throws GestorExcepcio en cas que no es pugui esborrar
     */
    public void esborraPista(String id) throws GestorExcepcio {
        // Step 1: Verify existence first to comply with exception requirements
        if (obtePista(id) == null) {
            throw new GestorExcepcio("Cannot delete: Pista not found with ID " + id);
        }

        String sql = "DELETE FROM pista WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new GestorExcepcio("Error while deleting the pista: " + e.getMessage());
        }
    }

    /**
     * Obté les pistes que utilitzen un remuntador específic
     *
     * @param remuntador el nom del remuntador
     * @return una llista amb les pistes que utilitzen aquest remuntador
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir les pistes
     */
    public List<Pista> obtePistesPerRemuntador(String remuntador) throws GestorExcepcio {
        List<Pista> pistaList = new ArrayList<>();
        // Step 1: Use the ANY operator to filter the PostgreSQL array
        String sql = "SELECT id FROM pista WHERE ? = ANY(remuntadors_acces)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, remuntador);
            ResultSet rs = ps.executeQuery();

            // Step 2: Iterate and use obtePista to ensure full object population
            while (rs.next()) {
                Pista p = obtePista(rs.getString("id"));
                if (p != null) pistaList.add(p);
            }
        } catch (SQLException e) {
            throw new GestorExcepcio("Error while searching by lift: " + e.getMessage());
        }
        return pistaList;
    }
}