package eac3.model;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class ItinerariRaquetes extends Pista {

    private String dificultat;
    private boolean circular;
    private boolean senyalitzat;
    private int tempsEstimat;

    public ItinerariRaquetes() {
    }

    public ItinerariRaquetes(String id, String nom, int longitud, boolean oberta,
            int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada,
            String dificultat, boolean circular, boolean senyalitzat,
            int tempsEstimat) {
        super(id, nom, longitud, oberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.dificultat = dificultat;
        this.circular = circular;
        this.senyalitzat = senyalitzat;
        this.tempsEstimat = tempsEstimat;
    }

   
    public String getDificultat() { return dificultat; }
    public void setDificultat(String dificultat) { this.dificultat = dificultat; }

    public boolean isCircular() { return circular; }
    public void setCircular(boolean circular) { this.circular = circular; }

    public boolean isSenyalitzat() { return senyalitzat; }
    public void setSenyalitzat(boolean senyalitzat) { this.senyalitzat = senyalitzat; }

    public int getTempsEstimat() { return tempsEstimat; }
    public void setTempsEstimat(int tempsEstimat) { this.tempsEstimat = tempsEstimat; }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dificultat, circular, senyalitzat, tempsEstimat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        final ItinerariRaquetes other = (ItinerariRaquetes) obj;
        return this.circular == other.circular &&
               this.senyalitzat == other.senyalitzat &&
               this.tempsEstimat == other.tempsEstimat &&
               Objects.equals(this.dificultat, other.dificultat);
    }
}