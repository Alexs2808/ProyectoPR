package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Pilotos;
import logica.Pistas;
import logica.TiemposSector;
import logica.TipoTester;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-12-09T19:58:18")
@StaticMetamodel(Practicas.class)
public class Practicas_ { 

    public static volatile ListAttribute<Practicas, TiemposSector> ListaTiemposSector;
    public static volatile SingularAttribute<Practicas, Integer> idPractica;
    public static volatile SingularAttribute<Practicas, Pistas> pista;
    public static volatile SingularAttribute<Practicas, Pilotos> piloto;
    public static volatile SingularAttribute<Practicas, TipoTester> tipoTester;

}