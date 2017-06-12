package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dVentas.class)
public abstract class dVentas_ {

	public static volatile SingularAttribute<dVentas, String> descripcion;
	public static volatile SingularAttribute<dVentas, Date> fecha;
	public static volatile SingularAttribute<dVentas, dOperadores> operadores;
	public static volatile SingularAttribute<dVentas, Integer> comprobante;
	public static volatile SingularAttribute<dVentas, dCuentasCorriente> cuenta;
	public static volatile SingularAttribute<dVentas, dTipoComprobante> tipoComprobante;
	public static volatile SingularAttribute<dVentas, Long> idVentas;
	public static volatile SingularAttribute<dVentas, Double> importe;
	public static volatile ListAttribute<dVentas, dDetalleVta> detalleVta;

}

