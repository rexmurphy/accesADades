/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex2;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Persona 
 * @author professor
 */

@XmlRootElement (name = "persona")
@XmlType(propOrder = {"nom","telefons"})
public class Persona {
    
    String nom;

    List<String> telefons=new ArrayList<>();

    @XmlElement
    public String getNom() {
        return nom;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElementWrapper(name="telefons")
    @XmlElement(name="telefon")  
    public List<String> getTelefons() {
        return telefons;
    }

}
