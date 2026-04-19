/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecte.video.orm;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;


/**
 *
 * @author m6
 */
public class ProjecteVideoORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf;
        EntityManager em;
        final String PU = "Video-ORMPU";
        
        Empleat emLluis= new Empleat("Lluis"),
                emAnna = new Empleat("Anna"),
                emJaume = new Empleat("Jaume");
        
        Departament dptFac = new Departament("Facturacio"),
                dptCom = new Departament("Comercial");
        
        dptFac.setCapDepartament(emAnna);
        dptCom.setCapDepartament(emLluis);
        dptCom.setNumMembres(4);
        
        
        emf=Persistence.createEntityManagerFactory(PU);
        
        em=emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.merge(dptFac); em.merge(dptCom); em.merge(emJaume);
        
        Query q = em.createQuery("Select e from Empleat e where e.dept.numMembres > :limit");
        q.setParameter("limit", 1);
        List<Empleat> l=q.getResultList();
        
        for(Empleat e:l){
            System.out.println(e.getNomEmpleat());
        }
        
        Query qUpd=em.createQuery("update Departament d set d.numMembres = d.numMembres*:factor" );
        qUpd.setParameter("factor", 2);
        qUpd.executeUpdate();
        
        
        em.getTransaction().commit();
        
        
    }
    
}
