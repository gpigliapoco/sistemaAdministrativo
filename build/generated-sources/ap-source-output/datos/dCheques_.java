package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dCheques.class)
public abstract class dCheques_ {

	public static volatile SingularAttribute<dCheques, Integer> Sucursal;
	public static volatile SingularAttribute<dCheques, Date> vencimiento;
	public static volatile SingularAttribute<dCheques, Date> fecha;
	public static volatile SingularAttribute<dCheques, dBanco> idBanco;
	public static volatile SingularAttribute<dCheques, Long> idCheques;
	public static volatile SingularAttribute<dCheques, Integer> numero;
	public static volatile SingularAttribute<dCheques, String> categoria;
	public static volatile SingularAttribute<dCheques, String> banco;
	public static volatile SingularAttribute<dCheques, ddetallecobros> detalleCobros;
	public static volatile SingularAttribute<dCheques, Double> importe;

}

