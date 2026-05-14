package eac3.dao;

import eac3.model.ItinerariRaquetes;
import java.util.List;
import eac3.repository.ItinerariRaquetesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Component DAO per manegar els objectes de la classe ItinerariRaquetes a la
 * base de dades
 */
@Repository
public class ItinerariRaquetesDao {
    
    @Autowired
    private ItinerariRaquetesRepository itinerariRaquetesRepository;

    /**
     * Consulta els itineraris de raquetes que tenen un temps estimat com a
     * màxim
     *
     * @param tempsEstimat el temps màxim
     * @return la llista d'itineraris de raquetes
     */
    public List<ItinerariRaquetes> getByTempsEstimat(int tempsEstimat) {
        return itinerariRaquetesRepository.getByTempsEstimat(tempsEstimat);
    }
}