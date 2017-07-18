package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dTransporte.class)
public abstract class dTransporte_ {

	public static volatile ListAttribute<dTransporte, dOperadores> operadores;
	public static volatile SingularAttribute<dTransporte, Long> idTrasnporte;
	public static volatile SingularAttribute<dTransporte, String> ciudad;
	public static volatile SingularAttribute<dTransporte, String> direccion;
	public static volatile SingularAttribute<dTransporte, String> telefono;
	public static volatile SingularAttribute<dTransporte, String> nombre;

}

