package datos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(dOperadores.class)
public abstract class dOperadores_ {

	public static volatile SingularAttribute<dOperadores, String> estado;
	public static volatile SingularAttribute<dOperadores, dTransporte> transporte;
	public static volatile SingularAttribute<dOperadores, String> direccion;
	public static volatile SingularAttribute<dOperadores, String> provincia;
	public static volatile SingularAttribute<dOperadores, dVendedor> vendedores;
	public static volatile SingularAttribute<dOperadores, Long> idoperadores;
	public static volatile SingularAttribute<dOperadores, String> razonSocial;
	public static volatile ListAttribute<dOperadores, dCompras> compras;
	public static volatile SingularAttribute<dOperadores, Integer> cuit;
	public static volatile SingularAttribute<dOperadores, dCategoria_op> categoriaOP;
	public static volatile SingularAttribute<dOperadores, String> observaciones;
	public static volatile ListAttribute<dOperadores, dVentas> ventas;
	public static volatile ListAttribute<dOperadores, dCuentasCorriente> cuentas;
	public static volatile SingularAttribute<dOperadores, String> localidad;
	public static volatile ListAttribute<dOperadores, dCobros> cobros;
	public static volatile SingularAttribute<dOperadores, String> telefono;
	public static volatile SingularAttribute<dOperadores, String> email;

}

