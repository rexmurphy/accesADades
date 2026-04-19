/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.video.orm;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Empleat implements Serializable{
    
    private String nomEmpleat;
    private Departament dept;


    
    public Empleat() {
    }

    public Empleat( String nomEmpleat) {
        this.nomEmpleat=nomEmpleat;
    }

    @Id
    public String getNomEmpleat() {
        return nomEmpleat;
    }

    public void setNomEmpleat(String nomEmpleat) {
        this.nomEmpleat = nomEmpleat;
    }

    @OneToOne(mappedBy="capDepartament")
    public Departament getDept() {
        return dept;
    }

    public void setDept(Departament dept) {
        this.dept = dept;
    }
   
}
