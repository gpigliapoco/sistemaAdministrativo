package datos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dIva.class)
public abstract class dIva_ {

	public static volatile SingularAttribute<dIva, Date> fecha;
	public static volatile SingularAttribute<dIva, dCompras> compras;
	public static volatile SingularAttribute<dIva, Double> otros;
	public static volatile SingularAttribute<dIva, dOperadores> operadores;
	public static volatile SingularAttribute<dIva, Double> ivaVta;
	public static volatile SingularAttribute<dIva, Double> ivaCpr;
	public static volatile SingularAttribute<dIva, dVentas> ventas;
	public static volatile SingularAttribute<dIva, Long> idIva;

}

