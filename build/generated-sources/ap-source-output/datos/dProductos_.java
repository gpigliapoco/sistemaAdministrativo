package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dProductos.class)
public abstract class dProductos_ {

	public static volatile SingularAttribute<dProductos, String> marca;
	public static volatile SingularAttribute<dProductos, Long> idProductos;
	public static volatile SingularAttribute<dProductos, dCategoriaPR> categoria;
	public static volatile SingularAttribute<dProductos, String> productos;

}

