/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import datos.dCobros;
import datos.ddetallecobros;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author BeltariT
 */
public class cobrosYpagosManager {
    
      private SessionFactory factory;

    public cobrosYpagosManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public List<dCobros> getbyCPRxOPxFac(Long id,Long fact) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From dCobros as c join fetch c.operadores as o where o.idoperadores= :id and c.idcobros= :fact ").
                    setParameter("id", id). 
                    setParameter("fact", fact).
                    list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }
        

    }
     
      public dCobros getbyDetalleCobro(Long id) {
        Session s = factory.openSession();

        try {
            return  (dCobros) s.createQuery("From dCobros as c join fetch c.operadores as o where c.idcobros= :id ").setParameter("id", id).uniqueResult();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }
      }
     public List<ddetallecobros> getbyCPRxOPxFac(Long id) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From ddetallecobros as d join fetch d.cobros as c  join fetch d.formaPago join fetch d.idBanco join fetch c.operadores  where c.idcobros= :id ").setParameter("id", id).list();
        
        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
     
     public ddetallecobros getbyDetalle(Long id) {
        Session s = factory.openSession();

        try {
            return  (ddetallecobros) s.createQuery("From ddetallecobros as d join fetch d.cobros as c  join fetch d.formaPago join fetch d.cheques as t join fetch t.detalleCobros join fetch t.idBanco join fetch c.operadores  where c.idcobros= :id ").setParameter("id", id).uniqueResult();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
     public ddetallecobros getbyDetalleEFECT(Long id) {
        Session s = factory.openSession();

        try {
            return  (ddetallecobros) s.createQuery("From ddetallecobros as d join fetch d.cobros as c  join fetch d.formaPago as f where d.formaPago=1 and c.idcobros= :id   ").setParameter("id", id).uniqueResult();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
}
