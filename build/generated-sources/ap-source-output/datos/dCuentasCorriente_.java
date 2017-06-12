package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dCuentasCorriente.class)
public abstract class dCuentasCorriente_ {

	public static volatile SingularAttribute<dCuentasCorriente, Double> haber;
	public static volatile SingularAttribute<dCuentasCorriente, Date> fecha;
	public static volatile SingularAttribute<dCuentasCorriente, Long> idCuentas;
	public static volatile SingularAttribute<dCuentasCorriente, dCompras> compras;
	public static volatile SingularAttribute<dCuentasCorriente, dOperadores> operadores;
	public static volatile SingularAttribute<dCuentasCorriente, String> numero;
	public static volatile SingularAttribute<dCuentasCorriente, Integer> numeroID;
	public static volatile SingularAttribute<dCuentasCorriente, dVentas> ventas;
	public static volatile SingularAttribute<dCuentasCorriente, Double> debe;
	public static volatile SingularAttribute<dCuentasCorriente, dCobros> cobros;
	public static volatile SingularAttribute<dCuentasCorriente, String> observacion;

}

