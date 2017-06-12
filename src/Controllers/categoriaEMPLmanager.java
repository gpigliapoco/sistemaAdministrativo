/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import conexion.SessionManager;
import datos.dCategoriaEMPL;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leo
 */
public class categoriaEMPLmanager {
    
      private SessionFactory factory;

    public categoriaEMPLmanager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dCategoriaEMPL getbyId(String categoria) {
        Session s = factory.openSession();

        try {
            return  (dCategoriaEMPL) s.createCriteria(dCategoriaEMPL.class).add( Restrictions.eq("seccion", categoria)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
    
}
