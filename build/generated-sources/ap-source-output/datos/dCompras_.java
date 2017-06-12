package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dCompras.class)
public abstract class dCompras_ {

	public static volatile SingularAttribute<dCompras, String> descripcion;
	public static volatile SingularAttribute<dCompras, Date> fecha;
	public static volatile SingularAttribute<dCompras, dOperadores> operadores;
	public static volatile SingularAttribute<dCompras, Long> idcompras;
	public static volatile SingularAttribute<dCompras, Integer> comprobante;
	public static volatile SingularAttribute<dCompras, dCuentasCorriente> cuenta;
	public static volatile SingularAttribute<dCompras, String> tipoComprobante;
	public static volatile SingularAttribute<dCompras, Double> importe;
	public static volatile ListAttribute<dCompras, dDetalleCpr> detalleCpr;

}

