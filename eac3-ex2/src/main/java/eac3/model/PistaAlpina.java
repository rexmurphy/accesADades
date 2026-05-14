package eac3.model;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class PistaAlpina extends Pista {

    private String color;
    private int pendentMaxim;

    public PistaAlpina() {}

    public PistaAlpina(String id, String nom, int longitud, boolean oberta, int desnivell, 
                        String color, int gruixNeu, String qualitatNeu, int pendentMaxim, boolean iluminada) {
        // Super debe seguir el orden de Pista: id, nom, longitud, oberta, desnivell, gruixNeu, qualitatNeu, iluminada
        super(id, nom, longitud, oberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.color = color;
        this.pendentMaxim = pendentMaxim;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPendentMaxim() {
        return pendentMaxim;
    }

    public void setPendentMaxim(int pendentMaxim) {
        this.pendentMaxim = pendentMaxim;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        final PistaAlpina other = (PistaAlpina) obj;
        return this.pendentMaxim == other.pendentMaxim && Objects.equals(this.color, other.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, pendentMaxim);
    }
}