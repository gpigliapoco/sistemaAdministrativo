/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dStock;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BeltariT
 */
public class stockManager {
    
      private SessionFactory factory;

    public stockManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dStock getbyIdStockVTA(Long id) {
        Session s = factory.openSession();

        try {
             
            return  (dStock) s.createQuery("from dStock as s join fetch s.idDetalleVta as d WHERE d.iddetalleVta= :id").setParameter("id", id).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
    
    public dStock getbyIdStockCPR(Long id) {
        Session s = factory.openSession();

        try {
             
            return  (dStock) s.createQuery("from dStock as s join fetch s.idDetalleCpr as d WHERE d.idDetalleCompra= :id").setParameter("id", id).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
}
