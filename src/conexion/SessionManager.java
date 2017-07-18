/*
 * SessionManager.java
 *
 */
package conexion;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Sebasti�n S. Sanga <SebastianSanga@gmail.com>
 */
public abstract class SessionManager {

    /**
     * Creates a new instance of SessionManager
     */
    
     // Atributo que guarda una fabrica de sesiones
     // Atributo que guarda una fabrica de sesiones
    private static SessionFactory factory;
    public SessionManager() {
    }

    public static Session getSession() throws HibernateException {

        // Instancia un objeto del tipo Configuration
        Configuration config = new Configuration();

        // Registra los mappers en la configuracion
        registerMappers(config);

        // Establece las propiedades de configuracion
        config.setProperties(getHibernateProperties());

        // Guarda la fabrica de sesiones
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        // Retorna una sesion de trabajo
        return factory.openSession();



    }

    private static Properties getHibernateProperties() {
        // Instancia un objeto del tipo Properties
        Properties props = new Properties();

        // Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

        // Establece la url de conexion dependiente del RDBMS
        props.put("hibernate.connection.url", "jdbc:mysql://192.168.100.103:3306/orestes_2");

        // Establece el usuario
        props.put("hibernate.connection.username", "root");

        // Establece la clave
        props.put("hibernate.connection.password", "password");

        // Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        
        // Establece el uso de logging, deber� existir el archivo log4j.properties
        props.put("hibernate.show_sql", "true");
       // props.put("hibernate.hbm2ddl.auto","update");

        // Retorna las propiedades
        return props;

    }

    private static void registerMappers(Configuration config) throws MappingException {
        config.addAnnotatedClass(datos.dOperadores.class);
        config.addAnnotatedClass(datos.dBanco.class);
        config.addAnnotatedClass(datos.dCategoria_op.class);
        config.addAnnotatedClass(datos.dCategoriaPR.class);
        config.addAnnotatedClass(datos.dCheques.class);
        config.addAnnotatedClass(datos.dCobros.class);
        config.addAnnotatedClass(datos.dCompras.class); 
        config.addAnnotatedClass(datos.dComisiones.class); 
        config.addAnnotatedClass(datos.ddetallecobros.class);
        config.addAnnotatedClass(datos.dDetalleCpr.class);
        config.addAnnotatedClass(datos.dDetalleVta.class);
        config.addAnnotatedClass(datos.dEmpleados.class);
        config.addAnnotatedClass(datos.dCategoriaEMPL.class);
        config.addAnnotatedClass(datos.dProductos.class);
        config.addAnnotatedClass(datos.dStock.class);
        config.addAnnotatedClass(datos.dPorcentajeComi.class); 
        config.addAnnotatedClass(datos.dTipoComprobante.class);
        config.addAnnotatedClass(datos.dTransporte.class);
        config.addAnnotatedClass(datos.dVendedor.class);
        config.addAnnotatedClass(datos.dVentas.class);
        config.addAnnotatedClass(datos.dFormaDePago.class);
        config.addAnnotatedClass(datos.dCuentasCorriente.class);
        config.addAnnotatedClass(datos.dSueldo.class);
        config.addAnnotatedClass(datos.dCategoriaVALE.class);
        config.addAnnotatedClass(datos.dIva.class);        
    }
}
