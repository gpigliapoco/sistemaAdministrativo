package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dFormaDePago.class)
public abstract class dFormaDePago_ {

	public static volatile SingularAttribute<dFormaDePago, Long> idFormaDePago;
	public static volatile SingularAttribute<dFormaDePago, String> formaPago;
	public static volatile ListAttribute<dFormaDePago, ddetallecobros> detalleCobro;

}

