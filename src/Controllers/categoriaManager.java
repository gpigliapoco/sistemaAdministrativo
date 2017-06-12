/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import conexion.SessionManager;
import datos.dCategoria_op;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author BeltariT
 */
public class categoriaManager {
    
    private SessionFactory factory;

    public categoriaManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
public dCategoria_op getbyId(String categoria) {
        Session s = factory.openSession();

        try {
            return  (dCategoria_op) s.createCriteria(dCategoria_op.class).add( Restrictions.eq("categoria", categoria)).uniqueResult();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }

    
}
