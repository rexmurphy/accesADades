package eac3.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Estacio {

    @Id
    private String id;
    private String nom;
    private String comarca;
    private int altitudMaxima;
    private String web;
    private String qualificacio;
    private double percentatgePistesObertes;

    @OneToMany(mappedBy = "estacio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pista> pistes = new ArrayList<>();

    public Estacio() {}

    public Estacio(String id, String nom, String comarca, int altitudMaxima, String web, String qualificacio, double percentatgePistesObertes) {
        this.id = id;
        this.nom = nom;
        this.comarca = comarca;
        this.altitudMaxima = altitudMaxima;
        this.web = web;
        this.qualificacio = qualificacio;
        this.percentatgePistesObertes = percentatgePistesObertes;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public List<Pista> getPistes() { 
        if (pistes == null) pistes = new ArrayList<>();
        return pistes; 
    }
    public void setPistes(List<Pista> pistes) { this.pistes = pistes; }
    public double getPercentatgePistesObertes() { return percentatgePistesObertes; }
    public void setPercentatgePistesObertes(double percentatgePistesObertes) { this.percentatgePistesObertes = percentatgePistesObertes; }

    public double calcularPercentatgeObertura() {
        if (getPistes().isEmpty()) {
            this.percentatgePistesObertes = 0.0;
            return 0.0;
        }
        long obertes = getPistes().stream().filter(Pista::isOberta).count();
        double percentatge = (obertes * 100.0) / getPistes().size();
        this.percentatgePistesObertes = percentatge;
        return percentatge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estacio other = (Estacio) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
