package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dSueldo.class)
public abstract class dSueldo_ {

	public static volatile SingularAttribute<dSueldo, Date> fecha;
	public static volatile SingularAttribute<dSueldo, dCategoriaVALE> categoriaVALE;
	public static volatile SingularAttribute<dSueldo, Long> IdSueldo;
	public static volatile SingularAttribute<dSueldo, dEmpleados> empleados;
	public static volatile SingularAttribute<dSueldo, Double> importe;
	public static volatile SingularAttribute<dSueldo, String> observacion;

}

