package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dCategoriaPR.class)
public abstract class dCategoriaPR_ {

	public static volatile SingularAttribute<dCategoriaPR, Long> idCategoriaPR;
	public static volatile SingularAttribute<dCategoriaPR, String> categoria;
	public static volatile ListAttribute<dCategoriaPR, dProductos> productos;

}

