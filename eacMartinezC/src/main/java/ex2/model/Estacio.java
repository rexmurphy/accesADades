/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author joan
 */
//TODO Afegir anotacions JAXB i GSON
@XmlRootElement (name="estacio")
@XmlType(propOrder={"nom", "comarca", "web", "altitudMaxima", "qualificacio", "estatObertura","pistes" })
public class Estacio {

    private String id;
    private String nom;
    private String comarca;
    private String web;
    
    @SerializedName("altitud_maxima")
    private int altitudMaxima;
    private String qualificacio;
    @SerializedName("estat_obertura_percentatge")
    private int estatObertura;

    private List<Pista> pistes = new ArrayList<>();

    public Estacio() {
    }

    public Estacio(String id, String nom, String comarca, String web, int altitudMaxima, String qualificacio, int estatObertura) {
        this.id = id;
        this.nom = nom;
        this.comarca = comarca;
        this.web = web;
        this.altitudMaxima = altitudMaxima;
        this.qualificacio = qualificacio;
        this.estatObertura = estatObertura;
        
    }
    public Estacio(String id, String nom, String comarca, String web, int altitudMaxima, String qualificacio, int estatObertura, List<Pista> pistes) {
        this(id, nom, comarca, web, altitudMaxima, qualificacio, estatObertura);
        this.pistes=pistes;
        
    }
    
    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @XmlElement(name="nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @XmlElement(name="comarca")
    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }
    @XmlElement(name="web")
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    @XmlElement(name="altitud-maxima")
    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }
    @XmlElement(name="qualificacio")
    public String getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(String qualificacio) {
        this.qualificacio = qualificacio;
    }
    @XmlElement(name="estat-obertura-percentatge")
    public int getEstatObertura() {
        return estatObertura;
    }

    public void setEstatObertura(int estatObertura) {
        this.estatObertura = estatObertura;
    }
    @XmlElementWrapper(name="pistes")
    @XmlElement(name="pista")
    public List<Pista> getPistes() {
        return pistes;
    }

    public void setPistes(List<Pista> pistes) {
        this.pistes = pistes;
    }

    /**
     * Calcula el percentatge de pistes obertes
     */
    public void actualitzaEstatObertura() {
        //Control if pistes is null or empty
        if (this.pistes ==null || this.pistes.isEmpty()){
           this.estatObertura=0;
        }
        int openCounter =0;
        for(Pista pista: this.pistes){
            if(pista.isOberta()){
                //Count true fields
                openCounter++;
            }  
        }
        //Calculte percentage of true open in order to refresh estatObertura
         double percentage= (double) openCounter/this.pistes.size()*100;
         this.estatObertura= (int) Math.round(percentage);
        
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.comarca);
        hash = 11 * hash + Objects.hashCode(this.web);
        hash = 11 * hash + Objects.hashCode(this.pistes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estacio other = (Estacio) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.comarca, other.comarca)) {
            return false;
        }
        if (!Objects.equals(this.web, other.web)) {
            return false;
        }
        return Objects.equals(this.pistes, other.pistes);
    }

}
