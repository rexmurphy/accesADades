/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package video;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author marc
 */


//Nota: M'he carregat aposta les anotacions per a evitar que me les mireu i traieu aquest punt gratis. És a dir, les anotacions que hi ha aquí son suficients per a que el programa no peti, però després els vostres mètodes no funcionaran si no.

@Entity
public class Persona implements Serializable {
    @Id
    private String nom;
    private int edat;

    public Persona() {
    }

    
    public Persona(String nom, int edat) {
        this.nom = nom;
        this.edat = edat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    
    
}
