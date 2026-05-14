package eac3.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Esta es la clave
public abstract class Pista {

    @Id
    private String id;
    private String nom;
    private int longitud;
    private boolean oberta;
    private int desnivell;
    private int gruixNeu;
    private String qualitatNeu;
    private boolean iluminada;

    @ManyToOne
    @JoinColumn(name = "estacio_id")
    private Estacio estacio;

    public Pista() {}

    public Pista(String id, String nom, int longitud, boolean oberta, int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada) {
        this.id = id;
        this.nom = nom;
        this.longitud = longitud;
        this.oberta = oberta;
        this.desnivell = desnivell;
        this.gruixNeu = gruixNeu;
        this.qualitatNeu = qualitatNeu;
        this.iluminada = iluminada;
    }

    // Getters y Setters manuales
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public boolean isOberta() { return oberta; }
    public void setOberta(boolean oberta) { this.oberta = oberta; }
    public int getGruixNeu() { return gruixNeu; }
    public void setGruixNeu(int gruixNeu) { this.gruixNeu = gruixNeu; }
    public Estacio getEstacio() { return estacio; }
    public void setEstacio(Estacio estacio) { this.estacio = estacio; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pista other = (Pista) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}