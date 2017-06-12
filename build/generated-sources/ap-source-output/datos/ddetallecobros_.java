package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ddetallecobros.class)
public abstract class ddetallecobros_ {

	public static volatile SingularAttribute<ddetallecobros, Integer> Sucursal;
	public static volatile SingularAttribute<ddetallecobros, Date> vencimiento;
	public static volatile SingularAttribute<ddetallecobros, dBanco> idBanco;
	public static volatile SingularAttribute<ddetallecobros, Integer> numero;
	public static volatile SingularAttribute<ddetallecobros, String> categoria;
	public static volatile SingularAttribute<ddetallecobros, String> banco;
	public static volatile SingularAttribute<ddetallecobros, dCobros> cobros;
	public static volatile SingularAttribute<ddetallecobros, dFormaDePago> formaPago;
	public static volatile SingularAttribute<ddetallecobros, Double> importe;
	public static volatile SingularAttribute<ddetallecobros, Long> iddetalleCobro;

}

