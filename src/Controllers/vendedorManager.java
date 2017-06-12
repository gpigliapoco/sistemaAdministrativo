/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dVendedor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BeltariT
 */
public class vendedorManager {
    
      private SessionFactory factory;

    public vendedorManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dVendedor getbyId(String nombre) {
        Session s = factory.openSession();

        try {
            return  (dVendedor) s.createCriteria(dVendedor.class).add( Restrictions.eq("nombre", nombre)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
     public dVendedor getbyId(Long id) {
        Session s = factory.openSession();

        try {
            return  (dVendedor) s.createCriteria(dVendedor.class).add( Restrictions.eq("idVendedor", id)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
}
