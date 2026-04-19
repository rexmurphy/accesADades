/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import javax.persistence.EntityManager;
import model.Estacio;
import model.Pista;

/**
 *
 * @author joan
 */
public class GestorPista {

    private final EntityManager em;

    /**
     * Crea un gestor de pistes utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorPista(EntityManager em) {
        this.em = em;
    }

    /**
     * Afegeix una pista a una estació existent.
     *
     * @param idEstacio identificador de l'estació on s'afegirà la pista.
     * @param pista     pista a afegir.
     * @throws GestorExcepcio si l'estació no existeix o si la pista ja existeix.
     */
    public void afegeixPista(String idEstacio, Pista pista) throws GestorExcepcio {
        //TODO Implementa el mètode
        try {
            em.getTransaction().begin();
            Estacio estacio = em.find(Estacio.class, idEstacio);
            if (estacio == null) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new GestorExcepcio("L'estació no existeix");
            }
            if (em.find(Pista.class, pista.getId()) != null) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new GestorExcepcio("La pista ja existeix");
            }

            pista.setEstacio(estacio);
            estacio.getPistes().add(pista);
            estacio.calcularPercentatgeObertura();
            
            em.persist(pista);
            em.getTransaction().commit();
        } catch (GestorExcepcio e) {
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new GestorExcepcio("Error en afegir la pista: " + e.getMessage());
        }
    }

    /**
     * Elimina una pista existent de la base de dades i recalcula
     * el percentatge de pistes obertes de la seva estació.
     *
     * @param idPista identificador de la pista a eliminar.
     * @throws GestorExcepcio si la pista no existeix
     */
    public void esborraPista(String idPista) throws GestorExcepcio {
        //TODO Implementa el mètode
        try {
            em.getTransaction().begin();
            Pista pista = em.find(Pista.class, idPista);
            if (pista == null) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new GestorExcepcio("La pista no existeix");
            }

            Estacio estacio = pista.getEstacio();
            estacio.getPistes().remove(pista);
            estacio.calcularPercentatgeObertura();
            
            em.remove(pista);
            em.getTransaction().commit();
        } catch (GestorExcepcio e) {
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new GestorExcepcio("Error en esborrar la pista: " + e.getMessage());
        }
    }

    /**
     * Actualitza el gruix de neu d'una pista aplicant-hi un increment.
     *
     * @param idPista   identificador de la pista a actualitzar.
     * @param increment valor a sumar al gruix de neu actual.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaGruixNeuPista(String idPista, int increment) throws GestorExcepcio {
        //TODO Implementa el mètode
        try {
            em.getTransaction().begin();
            Pista pista = em.find(Pista.class, idPista);
            if (pista == null) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new GestorExcepcio("La pista no existeix");
            }

            pista.setGruixNeu(pista.getGruixNeu() + increment);
            em.getTransaction().commit();
        } catch (GestorExcepcio e) {
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new GestorExcepcio("Error en actualitzar gruix: " + e.getMessage());
        }
    }

    /**
     * Actualitza l'estat d'obertura d'una pista i recalcula el percentatge
     * de pistes obertes de l'estació a la qual pertany.
     *
     * @param idPista identificador de la pista a actualitzar.
     * @param oberta  nou valor de l'estat d'obertura.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaObertaPista(String idPista, boolean oberta) throws GestorExcepcio {
        //TODO Implementa el mètode
        try {
            em.getTransaction().begin();
            Pista pista = em.find(Pista.class, idPista);
            if (pista == null) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new GestorExcepcio("La pista no existeix");
            }

            pista.setOberta(oberta);
            pista.getEstacio().calcularPercentatgeObertura();
            
            em.getTransaction().commit();
        } catch (GestorExcepcio e) {
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new GestorExcepcio("Error en actualitzar estat: " + e.getMessage());
        }
    }
}