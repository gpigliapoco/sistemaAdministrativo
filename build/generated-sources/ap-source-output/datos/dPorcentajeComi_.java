package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dPorcentajeComi.class)
public abstract class dPorcentajeComi_ {

	public static volatile SingularAttribute<dPorcentajeComi, Long> idPorcentaje;
	public static volatile SingularAttribute<dPorcentajeComi, dCategoriaPR> idCategoriaPR;
	public static volatile SingularAttribute<dPorcentajeComi, dVendedor> idVendedor;
	public static volatile SingularAttribute<dPorcentajeComi, Double> comisionPorc;

}

