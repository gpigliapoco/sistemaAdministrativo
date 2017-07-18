/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dCategoria_contabilidad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leo
 */
public class planCuentasManager {
    
     private SessionFactory factory;

    public planCuentasManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dCategoria_contabilidad getbyId(String categoria) {
        Session s = factory.openSession();

        try {
            return  (dCategoria_contabilidad) s.createCriteria(dCategoria_contabilidad.class).add( Restrictions.eq("categoria", categoria)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
    
}
