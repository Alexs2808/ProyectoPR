package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Practicas;
import logica.Usuarios;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-12-09T14:48:34")
@StaticMetamodel(Pilotos.class)
public class Pilotos_ { 

    public static volatile SingularAttribute<Pilotos, String> apellidoPaterno;
    public static volatile SingularAttribute<Pilotos, Usuarios> equipo;
    public static volatile ListAttribute<Pilotos, Practicas> ListaPracticas;
    public static volatile SingularAttribute<Pilotos, Integer> idPiloto;
    public static volatile SingularAttribute<Pilotos, Integer> edad;
    public static volatile SingularAttribute<Pilotos, String> nombrePiloto;
    public static volatile SingularAttribute<Pilotos, String> apellidoMaterno;
    public static volatile SingularAttribute<Pilotos, String> nacionalidad;
    public static volatile SingularAttribute<Pilotos, Boolean> activo;

}