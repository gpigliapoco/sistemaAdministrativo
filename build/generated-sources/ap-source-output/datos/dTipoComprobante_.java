package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dTipoComprobante.class)
public abstract class dTipoComprobante_ {

	public static volatile SingularAttribute<dTipoComprobante, Long> idTipo_comprobante;
	public static volatile SingularAttribute<dTipoComprobante, String> comprobante;
	public static volatile ListAttribute<dTipoComprobante, dVentas> ventas;

}

