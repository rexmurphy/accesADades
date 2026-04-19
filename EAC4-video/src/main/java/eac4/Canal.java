/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac4;

//////import javax.xml.bind.annotation.XmlElement;
//////import javax.xml.bind.annotation.XmlRootElement;

/**
 * Canal representa un canal de Twitch
 *
 * @author alumne
 */
//////@XmlRootElement
public class Canal {

    private int id;
    private String nom;
    private String tema;

    public Canal() {

    }

    public Canal(int id, String nom, String tema) {
        this.id = id;
        this.nom = nom;
        this.tema = tema;
    }

//////    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//////    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//////    @XmlElement
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

}