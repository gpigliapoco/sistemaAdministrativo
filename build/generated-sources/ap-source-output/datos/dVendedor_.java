package datos;

import java.sql.Blob;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dVendedor.class)
public abstract class dVendedor_ {

	public static volatile ListAttribute<dVendedor, dComisiones> comisiones;
	public static volatile ListAttribute<dVendedor, dOperadores> operadores;
	public static volatile SingularAttribute<dVendedor, Blob> foto;
	public static volatile ListAttribute<dVendedor, dPorcentajeComi> porcentajeComi;
	public static volatile SingularAttribute<dVendedor, Long> idVendedor;
	public static volatile SingularAttribute<dVendedor, String> direccion;
	public static volatile SingularAttribute<dVendedor, String> localidad;
	public static volatile SingularAttribute<dVendedor, String> provincia;
	public static volatile SingularAttribute<dVendedor, String> telefono;
	public static volatile SingularAttribute<dVendedor, String> nombre;
	public static volatile SingularAttribute<dVendedor, String> email;
	public static volatile SingularAttribute<dVendedor, String> observacion;

}

