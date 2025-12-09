package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TiemposSector implements Serializable {
    
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY) 
   private Integer idTiempoSector;
   @ManyToOne
   @JoinColumn(name="id_practica")
   private Practicas practica;//relacion
   private Integer numVuelta;//1-6 vueltas por piloto
   private Integer numSector;
   private float tiempoSector;
   private float ritmo;

    public TiemposSector() {
    }

    public TiemposSector(Integer idTiempoSector, Practicas practica, Integer numVuelta, Integer numSector, float tiempoSector, float ritmo) {
        this.idTiempoSector = idTiempoSector;
        this.practica = practica;
        this.numVuelta = numVuelta;
        this.numSector = numSector;
        this.tiempoSector = tiempoSector;
        this.ritmo = ritmo;
        
        
    }

    public Integer getIdTiempoSector() {
        return idTiempoSector;
    }

    public void setIdTiempoSector(Integer idTiempoSector) {
        this.idTiempoSector = idTiempoSector;
    }

    public Practicas getPractica() {
        return practica;
    }

    public void setPractica(Practicas practica) {
        this.practica = practica;
    }

    public Integer getNumVuelta() {
        return numVuelta;
    }

    public void setNumVuelta(Integer numVuelta) {
        this.numVuelta = numVuelta;
    }

    public Integer getNumSector() {
        return numSector;
    }

    public void setNumSector(Integer numSector) {
        this.numSector = numSector;
    }

    public float getTiempoSector() {
        return tiempoSector;
    }

    public void setTiempoSector(float tiempoSector) {
        this.tiempoSector = tiempoSector;
    }

    public float getRitmo() {
        return ritmo;
    }

    public void setRitmo(float ritmo) {
        this.ritmo = ritmo;
    }
    
   
   
}
