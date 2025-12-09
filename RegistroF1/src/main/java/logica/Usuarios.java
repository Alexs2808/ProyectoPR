package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuarios implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Esto genera el ID de forma automatica
    private Integer idEquipo;
    private String nombreEquipo;
    private String nombreUsuario;
    private String contrasenia;
    @OneToMany(mappedBy = "equipo")
    private List<Pilotos>ListaPilotos;

    public Usuarios() {
    }

    public Usuarios(Integer idEquipo, String nombreEquipo, String nombreUsuario, String contrasenia, List<Pilotos> ListaPilotos) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.ListaPilotos = ListaPilotos;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Pilotos> getListaPilotos() {
        return ListaPilotos;
    }

    public void setListaPilotos(List<Pilotos> ListaPilotos) {
        this.ListaPilotos = ListaPilotos;
    }

    
    
}
