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

@Entity
public class Practicas implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPractica;
    @ManyToOne
    @JoinColumn(name="id_pista")
    private Pistas pista; //relacion
    @ManyToOne
    @JoinColumn(name="id_piloto")
    private Pilotos piloto;//relacion
    @ManyToOne
    @JoinColumn(name="id_tipo_tester")    
    private TipoTester tipoTester;//relacion
    @OneToMany(mappedBy = "practica")
    private List<TiemposSector>ListaTiemposSector;

    //private String Fecha;

    public Practicas() {
    }

    public Practicas(Integer idPractica, Pistas pista, Pilotos piloto, TipoTester tipoTester, List<TiemposSector> ListaTiemposSector) {
        this.idPractica = idPractica;
        this.pista = pista;
        this.piloto = piloto;
        this.tipoTester = tipoTester;
        this.ListaTiemposSector = ListaTiemposSector;
    }
    
    
    

    public Integer getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(Integer idPractica) {
        this.idPractica = idPractica;
    }

    public Pistas getPista() {
        return pista;
    }

    public void setPista(Pistas pista) {
        this.pista = pista;
    }

    public Pilotos getPiloto() {
        return piloto;
    }

    public void setPiloto(Pilotos piloto) {
        this.piloto = piloto;
    }

    public TipoTester getTipoTester() {
        return tipoTester;
    }

    public void setTipoTester(TipoTester tipoTester) {
        this.tipoTester = tipoTester;
    }

    public List<TiemposSector> getListaTiemposSector() {
        return ListaTiemposSector;
    }

    public void setListaTiemposSector(List<TiemposSector> ListaTiemposSector) {
        this.ListaTiemposSector = ListaTiemposSector;
    }
    
    
    
}
