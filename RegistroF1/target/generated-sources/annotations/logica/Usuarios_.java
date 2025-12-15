package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Pilotos;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-12-14T18:59:25")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, Integer> idEquipo;
    public static volatile SingularAttribute<Usuarios, String> nombreEquipo;
    public static volatile SingularAttribute<Usuarios, String> contrasenia;
    public static volatile SingularAttribute<Usuarios, String> nombreUsuario;
    public static volatile ListAttribute<Usuarios, Pilotos> ListaPilotos;

}