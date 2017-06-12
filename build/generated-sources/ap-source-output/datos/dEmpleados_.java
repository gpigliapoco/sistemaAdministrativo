package datos;

import java.sql.Blob;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dEmpleados.class)
public abstract class dEmpleados_ {

	public static volatile SingularAttribute<dEmpleados, Blob> foto;
	public static volatile SingularAttribute<dEmpleados, Date> ingreso;
	public static volatile SingularAttribute<dEmpleados, dCategoriaEMPL> categoria;
	public static volatile SingularAttribute<dEmpleados, String> direccion;
	public static volatile SingularAttribute<dEmpleados, String> localidad;
	public static volatile SingularAttribute<dEmpleados, Long> idEmpleados;
	public static volatile SingularAttribute<dEmpleados, String> telefono;
	public static volatile SingularAttribute<dEmpleados, String> nombre;
	public static volatile SingularAttribute<dEmpleados, Integer> dni;
	public static volatile SingularAttribute<dEmpleados, String> observacion;
	public static volatile SingularAttribute<dEmpleados, Date> nacimiento;
	public static volatile SingularAttribute<dEmpleados, String> nacionalidad;

}

