/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import conexion.SessionManager;
import datos.dCategoriaPR;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BeltariT
 */
public class categoriaPRmanager {
    
      private SessionFactory factory;

    public categoriaPRmanager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dCategoriaPR getbyId(String categoria) {
        Session s = factory.openSession();

        try {
            return  (dCategoriaPR) s.createCriteria(dCategoriaPR.class).add( Restrictions.eq("categoria", categoria)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
}
