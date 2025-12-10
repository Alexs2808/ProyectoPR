
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pilotos implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPiloto;
    private String nombrePiloto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nacionalidad;
    private Integer edad;
    private boolean activo = true;
    @ManyToOne
    @JoinColumn(name="id_equipo")
    private Usuarios equipo;
    @OneToMany(mappedBy = "piloto")
    private List<Practicas>ListaPracticas;

    public Pilotos() {
    }

    public Pilotos(Integer idPiloto, String nombrePiloto, String apellidoPaterno, String apellidoMaterno, String nacionalidad, Integer edad, boolean activo, Usuarios equipo, List<Practicas> ListaPracticas) {
        this.idPiloto = idPiloto;
        this.nombrePiloto = nombrePiloto;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.activo = activo;
        this.equipo = equipo;
        this.ListaPracticas = ListaPracticas;
    }

    public Integer getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(Integer idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuarios getEquipo() {
        return equipo;
    }

    public void setEquipo(Usuarios equipo) {
        this.equipo = equipo;
    }

    public List<Practicas> getListaPracticas() {
        return ListaPracticas;
    }

    public void setListaPracticas(List<Practicas> ListaPracticas) {
        this.ListaPracticas = ListaPracticas;
    }
    
    
    
    
}
