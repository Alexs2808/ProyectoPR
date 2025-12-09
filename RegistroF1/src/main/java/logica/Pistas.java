package logica;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pistas implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPista;
    private String nombrePista;
    private float distancia;
    @OneToMany(mappedBy="pista")
    private List<Practicas>listaPracticas;

    public Pistas() {
    }

    public Pistas(int idPista, String nombrePista, float distancia, List<Practicas> listaPracticas) {
        this.idPista = idPista;
        this.nombrePista = nombrePista;
        this.distancia = distancia;
        this.listaPracticas = listaPracticas;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public String getNombrePista() {
        return nombrePista;
    }

    public void setNombrePista(String nombrePista) {
        this.nombrePista = nombrePista;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public List<Practicas> getListaPracticas() {
        return listaPracticas;
    }

    public void setListaPracticas(List<Practicas> listaPracticas) {
        this.listaPracticas = listaPracticas;
    }

    
    
    
    
}
