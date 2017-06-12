package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dDetalleCpr.class)
public abstract class dDetalleCpr_ {

	public static volatile SingularAttribute<dDetalleCpr, Double> precio;
	public static volatile SingularAttribute<dDetalleCpr, dCompras> compras;
	public static volatile SingularAttribute<dDetalleCpr, Long> idDetalleCompra;
	public static volatile SingularAttribute<dDetalleCpr, Integer> cantidad;
	public static volatile ListAttribute<dDetalleCpr, dStock> stock;
	public static volatile SingularAttribute<dDetalleCpr, dProductos> productos;

}

