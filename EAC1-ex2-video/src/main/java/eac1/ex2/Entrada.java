/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex2;

//l'element es diu persona en el XML

import javax.xml.bind.annotation.*;

@XmlRootElement (name = "entrada")
//l'ordre en que apareixeran els seus elements interns en el XML
@XmlType(propOrder = {"nom","tipus"})
public class Entrada {
    
    int id;
    String nom, tipus;

    //es un atribut. els atributs son els que es posen a la propia etiqueta arrel
    @XmlAttribute
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    //es un element. Com que no especifiquem name, s'assumeix que es diu nom
    @XmlElement
    public String getNom() {
        return nom;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    //aquests tags es posen sempre abans del getter
    @XmlElement
    public String getTipus() {
        return tipus;
    }
    

    public void setTipus(String t) {
        this.tipus = t;
    }
}