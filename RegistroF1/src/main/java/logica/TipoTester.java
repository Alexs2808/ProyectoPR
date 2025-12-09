package logica;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoTester implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idTipoTester;
    private String nombreTester;
    @OneToMany(mappedBy = "tipoTester")
    private List<Practicas>listaPracticas;

    public TipoTester() {
    }

    public TipoTester(int idTipoTester, String nombreTester, List<Practicas> listaPracticas) {
        this.idTipoTester = idTipoTester;
        this.nombreTester = nombreTester;
        this.listaPracticas = listaPracticas;
    }

    public int getIdTipoTester() {
        return idTipoTester;
    }

    public void setIdTipoTester(int idTipoTester) {
        this.idTipoTester = idTipoTester;
    }

    public String getNombreTester() {
        return nombreTester;
    }

    public void setNombreTester(String nombreTester) {
        this.nombreTester = nombreTester;
    }

    public List<Practicas> getListaPracticas() {
        return listaPracticas;
    }

    public void setListaPracticas(List<Practicas> listaPracticas) {
        this.listaPracticas = listaPracticas;
    }
    
    
    
            
            
}
