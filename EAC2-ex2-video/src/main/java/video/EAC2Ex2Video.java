/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package video;

/**
 *
 * @author ioc
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EAC2Ex2Video {

    private static final String PU = "ObjectDB";

       
    private static EntityManagerFactory emFactory;
    private static EntityManager em;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        emFactory = Persistence.createEntityManagerFactory(PU);
        em = emFactory.createEntityManager();  
        
        ///
        
        Persona p = new Persona("Marc",28);
        
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        
        
    }
    
}
