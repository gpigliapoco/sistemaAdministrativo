package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dCobros.class)
public abstract class dCobros_ {

	public static volatile SingularAttribute<dCobros, String> descripcion;
	public static volatile SingularAttribute<dCobros, Date> fecha;
	public static volatile SingularAttribute<dCobros, dOperadores> operadores;
	public static volatile SingularAttribute<dCobros, String> comprobante;
	public static volatile SingularAttribute<dCobros, dCuentasCorriente> cuenta;
	public static volatile SingularAttribute<dCobros, String> tipoComprobante;
	public static volatile SingularAttribute<dCobros, Double> importe;
	public static volatile ListAttribute<dCobros, ddetallecobros> detalleCobro;
	public static volatile SingularAttribute<dCobros, Long> idcobros;

}

