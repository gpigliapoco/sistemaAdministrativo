/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dCompras;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author BeltariT
 */
public class comprasManager {
    
      private SessionFactory factory;

    public comprasManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dCompras getbyDetalle(Long id) {
        Session s = factory.openSession();

        try {
            return  (dCompras) s.createQuery("From dCompras as c join fetch c.operadores as o join fetch o.categoriaOP join fetch c.detalleCpr as t join fetch t.productos where c.idcompras= :id ").setParameter("id", id).uniqueResult();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
      public List<dCompras> getbyCPRxOP(Long id) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From dCompras as c join fetch c.operadores as o join fetch o.categoriaOP where o.idoperadores= :id ").setParameter("id", id).list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
      public List<dCompras> getbyCPRxOPxFac(Long id,Long fact) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From dCompras as c join fetch c.operadores as o join fetch o.categoriaOP where o.idoperadores= :id and c.idcompras= :fact ").
                    setParameter("id", id).
                    setParameter("fact", fact).
                    list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
      
      public dCompras getByIdcompras(Long dato){
    
        Session s = factory.openSession();
         try {
             return  (dCompras) s.createQuery("From dCompras as c where c.idcompras= :id").setParameter("id", dato).uniqueResult();
         } finally{
          s.close();
             
         }
  
    
    }
}
