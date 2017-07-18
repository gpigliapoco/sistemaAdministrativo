package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dStock.class)
public abstract class dStock_ {

	public static volatile SingularAttribute<dStock, Long> idStock;
	public static volatile SingularAttribute<dStock, Date> fecha;
	public static volatile SingularAttribute<dStock, dDetalleVta> idDetalleVta;
	public static volatile SingularAttribute<dStock, Integer> cantidad;
	public static volatile SingularAttribute<dStock, dProductos> producto;
	public static volatile SingularAttribute<dStock, dDetalleCpr> idDetalleCpr;
	public static volatile SingularAttribute<dStock, Integer> salida;

}

