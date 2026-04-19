package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "pista")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipus_pista", discriminatorType = DiscriminatorType.STRING)
public abstract class Pista implements Serializable {

    @Id
    @Column(length = 14)
    private String id;

    @Column(length = 100, nullable = false)
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

    // ==================== CONSTRUCTORS ====================

    protected Pista() {
        // Required by JPA
    }

    public Pista(String id, String nom, int longitud, boolean oberta,
                 int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada) {
        this.id = id;
        this.nom = nom;
        this.longitud = longitud;
        this.oberta = oberta;
        this.desnivell = desnivell;
        this.gruixNeu = gruixNeu;
        this.qualitatNeu = qualitatNeu;
        this.iluminada = iluminada;
    }

    // ==================== GETTERS ====================

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getLongitud() {
        return longitud;
    }

    public boolean isOberta() {
        return oberta;
    }

    public int getDesnivell() {
        return desnivell;
    }

    public int getGruixNeu() {
        return gruixNeu;
    }

    public String getQualitatNeu() {
        return qualitatNeu;
    }

    public boolean isIluminada() {
        return iluminada;
    }

    public Estacio getEstacio() {
        return estacio;
    }

    // ==================== SETTERS ====================

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    public void setDesnivell(int desnivell) {
        this.desnivell = desnivell;
    }

    public void setGruixNeu(int gruixNeu) {
        this.gruixNeu = gruixNeu;
    }

    public void setQualitatNeu(String qualitatNeu) {
        this.qualitatNeu = qualitatNeu;
    }

    public void setIluminada(boolean iluminada) {
        this.iluminada = iluminada;
    }

    public void setEstacio(Estacio estacio) {
        this.estacio = estacio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return longitud == pista.longitud && oberta == pista.oberta &&
                desnivell == pista.desnivell && gruixNeu == pista.gruixNeu &&
                iluminada == pista.iluminada && Objects.equals(id, pista.id) &&
                Objects.equals(nom, pista.nom) &&
                Objects.equals(qualitatNeu, pista.qualitatNeu) &&
                (estacio == null || pista.estacio == null ? estacio == pista.estacio : Objects.equals(estacio.getId(), pista.estacio.getId()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, longitud, oberta, desnivell, gruixNeu, qualitatNeu, iluminada);
    }
}