/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

/**
 *
 * @author Marc
 */
public class Persona {
    
    private String nom;
    private String colorPreferit;

    public Persona() {
    }

    public Persona(String nom, String colorPreferit) {
        this.nom = nom;
        this.colorPreferit = colorPreferit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColorPreferit() {
        return colorPreferit;
    }

    public void setColorPreferit(String colorPreferit) {
        this.colorPreferit = colorPreferit;
    }
    
    
    
}
