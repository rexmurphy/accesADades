/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Pista;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

/**
 * Classe que gestiona la persistència dels objectes de la classe model.Pista
 *
 * @author professor
 */
public class PistaManager {

    public static final String ARREL = "doc(\"pistes/pistes.xml\")/";

    private final XQConnection conn;

    public PistaManager(XQConnection conn) {
        this.conn = conn;
    }

    /**
     * Dóna d'alta una pista a la base de dades
     *
     * @param pista la pista a donar d'alta
     * @throws ManagerException en cas d'error, com per exemple clau duplicada
     */
    public void insert(Pista pista) throws ManagerException {
        try {
            if (exists(pista.getId())) {
                throw new ManagerException("La pista amb ID " + pista.getId() + " ja existeix.");
            }
            String xmlPista = Utilitats.formaObjecteXML(pista);
            // Sintaxi BaseX: insert node ... into ...
            String consulta = "insert node " + xmlPista + " into " + ARREL + "pistes";
            XQExpression exp = conn.createExpression();
            exp.executeQuery(consulta);
        } catch (XQException ex) {
            throw new ManagerException("Error en inserir la pista", ex);
        }
    }

    /**
     * Elimina una pista de la base de dades
     *
     * @param id l'identificador de la pista
     * @throws ManagerException en cas d'error, com per exemple que no existeixi
     */
    public void delete(String id) throws ManagerException {
        try {
            if (!exists(id)) {
                throw new ManagerException("La pista amb ID " + id + " no existeix.");
            }
            // Sintaxi BaseX: delete node ...
            String consulta = "delete node " + ARREL + "pistes/pista[@id='" + id + "']";
            XQExpression exp = conn.createExpression();
            exp.executeQuery(consulta);
        } catch (XQException ex) {
            throw new ManagerException("Error en eliminar la pista", ex);
        }
    }

    /**
     * Elimina totes les pistes de la base de dades
     *
     * @throws ManagerException
     */
    public void deleteAll() throws ManagerException {
        try {
            // Sintaxi BaseX: delete nodes ...
            String consulta = "delete nodes " + ARREL + "pistes/pista";
            XQExpression exp = conn.createExpression();
            exp.executeQuery(consulta);
        } catch (XQException ex) {
            Logger.getLogger(PistaManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new ManagerException("Error en esborrar totes les pistes", ex);
        }
    }

    /**
     * Obté una pista de la base de dades
     *
     * @param id l'identificador de la pista
     * @return l'objecte pista
     * @throws ManagerException en cas d'error, com per exemple que no existeixi
     */
    public Pista getPista(String id) throws ManagerException {
        try {
            String consulta = ARREL + "pistes/pista[@id='" + id + "']";
            XQExpression exp = conn.createExpression();
            XQResultSequence rs = exp.executeQuery(consulta);

            if (rs.next()) {
                return Utilitats.obteObjecte(rs.getItemAsString(null));
            } else {
                throw new ManagerException("La pista amb ID " + id + " no existeix.");
            }
        } catch (XQException ex) {
            throw new ManagerException("Error en obtenir la pista", ex);
        }
    }

    /**
     * Obtenir les pistes que tenen un tipus de remuntador
     *
     * @param tipusRemuntador tipus del remuntador
     * @return la llista de pistes
     */
    public List<Pista> getPistesAmbRemuntador(String tipusRemuntador) throws ManagerException {
        List<Pista> llista = new ArrayList<>();
        try {
            String consulta = ARREL + "pistes/pista[remuntadors/remuntador='" + tipusRemuntador + "']";
            XQExpression exp = conn.createExpression();
            XQResultSequence rs = exp.executeQuery(consulta);

            while (rs.next()) {
                llista.add(Utilitats.obteObjecte(rs.getItemAsString(null)));
            }
        } catch (XQException ex) {
            throw new ManagerException("Error en filtrar pistes per remuntador", ex);
        }
        return llista;
    }

    private boolean exists(String id) throws XQException {
        String consulta = "exists(" + ARREL + "pistes/pista[@id='" + id + "'])";
        XQExpression exp = conn.createExpression();
        XQResultSequence rs = exp.executeQuery(consulta);
        if (rs.next()) {
            return rs.getBoolean();
        }
        return false;
    }
}