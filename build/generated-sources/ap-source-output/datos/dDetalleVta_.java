package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dDetalleVta.class)
public abstract class dDetalleVta_ {

	public static volatile ListAttribute<dDetalleVta, dComisiones> comisiones;
	public static volatile SingularAttribute<dDetalleVta, Double> precio;
	public static volatile SingularAttribute<dDetalleVta, Long> iddetalleVta;
	public static volatile SingularAttribute<dDetalleVta, dVentas> ventas;
	public static volatile SingularAttribute<dDetalleVta, Integer> cantidad;
	public static volatile ListAttribute<dDetalleVta, dStock> stock;
	public static volatile SingularAttribute<dDetalleVta, dProductos> productos;

}

