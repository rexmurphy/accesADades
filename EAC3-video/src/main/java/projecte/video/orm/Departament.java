
package projecte.video.orm;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Departament implements Serializable{
    
    private String nomDepartament;
    private int numMembres;
    private Empleat capDepartament;
       
    
    public Departament() {
    }

    public Departament( String nomDepartament) {
        this.nomDepartament=nomDepartament;
    }

    @Id
    @Column(name="nom_departament")
    public String getNomDepartament() {
        return nomDepartament;
    }

    public void setNomDepartament(String nomDepartament) {
        this.nomDepartament = nomDepartament;
    }

    @Column(name="num_members")
    public int getNumMembres() {
        return numMembres;
    }

    public void setNumMembres(int numMembres) {
        this.numMembres = numMembres;
    }

    @OneToOne
    @JoinColumn(name="cap")
    public Empleat getCapDepartament() {
        return capDepartament;
    }

    public void setCapDepartament(Empleat capDepartament) {
        this.capDepartament = capDepartament;
    }
   
}