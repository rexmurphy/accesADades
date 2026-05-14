package eac3.model;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class PistaNordica extends Pista {

    private String estil;
    private boolean trepitjada;

    public PistaNordica() {}

    public PistaNordica(String id, String nom, int longitud, boolean oberta, int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada, String estil, boolean trepitjada) {
        super(id, nom, longitud, oberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.estil = estil;
        this.trepitjada = trepitjada;
    }

    public String getEstil() { return estil; }
    public void setEstil(String estil) { this.estil = estil; }
    public boolean isTrepitjada() { return trepitjada; }
    public void setTrepitjada(boolean trepitjada) { this.trepitjada = trepitjada; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        PistaNordica that = (PistaNordica) obj;
        return trepitjada == that.trepitjada && Objects.equals(estil, that.estil);
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), estil, trepitjada); }
}