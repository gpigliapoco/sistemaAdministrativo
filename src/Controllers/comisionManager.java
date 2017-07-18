/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dComisiones;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author BeltariT
 */
public class comisionManager {
    
      private SessionFactory factory;

    public comisionManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dComisiones getbyIdComision(Long id) {
        Session s = factory.openSession();

        try {
             
            return   (dComisiones) s.createQuery("from dComisiones as s join fetch s.idDetalleVta as d WHERE d.iddetalleVta= :id").setParameter("id", id).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
    
}
