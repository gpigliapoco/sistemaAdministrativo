package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dComisiones.class)
public abstract class dComisiones_ {

	public static volatile SingularAttribute<dComisiones, Date> fecha;
	public static volatile SingularAttribute<dComisiones, Double> precio;
	public static volatile SingularAttribute<dComisiones, dDetalleVta> idDetalleVta;
	public static volatile SingularAttribute<dComisiones, Long> idComisiones;
	public static volatile SingularAttribute<dComisiones, Double> comision;
	public static volatile SingularAttribute<dComisiones, dVendedor> idVendedor;
	public static volatile SingularAttribute<dComisiones, Integer> cantidad;
	public static volatile SingularAttribute<dComisiones, dProductos> idProducto;

}

