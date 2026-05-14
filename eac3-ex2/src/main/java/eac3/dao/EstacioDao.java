package eac3.dao;

import eac3.gestors.GestorExcepcio;
import eac3.model.Estacio;
import eac3.model.Pista;
import eac3.repository.EstacioRepository;
import eac3.repository.PistaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstacioDao {
    
    @Autowired
    private EstacioRepository estacioRepository;

    @Autowired
    private PistaRepository pistaRepository;

    public void deleteAll() {
        estacioRepository.deleteAll();
    }

    public void insert(Estacio estacio) throws GestorExcepcio {
        if (!estacioRepository.existsById(estacio.getId())) {
            estacioRepository.save(estacio);
        } else {
            throw new GestorExcepcio("L'estacio ja existeix");
        }
    }

    @Transactional
    public void delete(String idEstacio) throws GestorExcepcio {
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        if (estacio != null) {
            if (estacio.getPistes() != null) {
                for (Pista p : estacio.getPistes()) {
                    pistaRepository.delete(p);
                }
            }
            estacioRepository.delete(estacio);
        } else {
            throw new GestorExcepcio("L'estacio no existeix");
        }
    }

    public List<Estacio> getAll() {
        return estacioRepository.findAll();
    }

    public Estacio findById(String idEstacio) throws GestorExcepcio {
        return estacioRepository.findById(idEstacio).orElseThrow(() -> new GestorExcepcio("L'estacio no existeix"));
    }

    @Transactional
    public void addPista(String idEstacio, Pista pista) throws GestorExcepcio {
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        if (estacio == null) {
            throw new GestorExcepcio("L'estacio no existeix");
        }
        
        // Verificamos si la pista ya está asociada
        if (estacio.getPistes().contains(pista)) {
            throw new GestorExcepcio("La pista ja es a l'estacio");
        }

        pista.setEstacio(estacio);
        estacio.getPistes().add(pista);
        estacio.calcularPercentatgeObertura();

        pistaRepository.save(pista);
        estacioRepository.save(estacio);
    }

    @Transactional
    public void removePista(String idEstacio, String idPista) throws GestorExcepcio {
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        Pista pista = pistaRepository.findById(idPista).orElse(null);

        if (estacio == null || pista == null) {
            throw new GestorExcepcio("Referencia no trobada");
        }
        
        if (!estacio.getPistes().remove(pista)) {
            throw new GestorExcepcio("La pista no es a l'estacio");
        }

        estacio.calcularPercentatgeObertura();
        
        pistaRepository.delete(pista);
        estacioRepository.save(estacio);
    }
}