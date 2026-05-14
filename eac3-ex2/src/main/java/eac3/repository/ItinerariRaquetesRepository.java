package eac3.repository;

import eac3.model.ItinerariRaquetes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositori per manegar els objectes de la classe ItinerariRaquetes a la base de dades
 */
@Repository
public interface ItinerariRaquetesRepository extends JpaRepository<ItinerariRaquetes, String> {

    /**
     * Consulta els itineraris de raquetes amb un temps estimat menor o igual
     *
     * @param tempsEstimat temps estimat màxim
     * @return la llista d'itineraris de raquetes
     */
    @Query("SELECT i FROM ItinerariRaquetes i WHERE i.tempsEstimat <= :tempsEstimat")
    public List<ItinerariRaquetes> getByTempsEstimat(@Param("tempsEstimat") int tempsEstimat);
}