package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuarios implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) //Esto genera el ID de forma automatica
    private int id;
    private String NombreEquipo;
    private String NombreUsuario;
    private String Contrasenia;

    public Usuarios() {
    }

    public Usuarios(int id, String NombreEquipo, String NombreUsuario, String Contrasenia) {
        this.id = id;
        this.NombreEquipo = NombreEquipo;
        this.NombreUsuario = NombreUsuario;
        this.Contrasenia = Contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String NombreEquipo) {
        this.NombreEquipo = NombreEquipo;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }
    
    
    
}
