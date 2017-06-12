/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dDetalleVta;
import datos.dVentas;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BeltariT
 */
public class ventasManager {
    
      private SessionFactory factory;

    public ventasManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public List<dVentas> getbyId(Long id) {
        Session s = factory.openSession();

        try {
            return   s.createQuery("FROM dVentas v  join fetch v.tipoComprobante join fetch v.operadores as o join fetch o.categoriaOP join fetch o.vendedores   where v.idVentas= :id").setParameter("id", id).list();
            
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
     public List<dVentas> getbyVTAxOP(Long id) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From dVentas as v join fetch v.tipoComprobante join fetch v.operadores as o join fetch o.categoriaOP join fetch o.vendedores  where o.idoperadores= :id ").setParameter("id", id).list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
      public List<dVentas> getbyVTAxOPxFac(Long id,Long fact) {
        Session s = factory.openSession();

        try {
            return s.createQuery("From dVentas as v join fetch v.tipoComprobante join fetch v.operadores as o  join fetch o.categoriaOP join fetch o.vendedores  where o.idoperadores= :id and v.idVentas= :fact ").
                    setParameter("id", id).
                    setParameter("fact", fact).
                    list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
     
       public dVentas getbyDetalle(Long id) {
        Session s = factory.openSession();

        try {
            return  (dVentas) s.createQuery("From dVentas as v join fetch v.tipoComprobante join fetch v.operadores as o join fetch o.categoriaOP join fetch o.vendedores join fetch v.detalleVta as t join fetch t.productos where v.idVentas= :id ").setParameter("id", id).uniqueResult();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
        public List<dVentas>  getbyVTAxOPxFacc(Long id,int fact){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dVentas.class).
                   setFetchMode("tipoComprobante", FetchMode.JOIN).createAlias("tipoComprobante", "c").
                   setFetchMode("operadores", FetchMode.JOIN).createAlias("operadores", "o").
                   add(Restrictions.like("o.idOperadores", "%"+id+"%")).
                   add(Restrictions.like("comprobante", "%"+fact+"%"))
            .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
       public List<dDetalleVta>  getbyVTA_anteriores(Long id,int fact){
      Session s = factory.openSession();
         try {
           return  s.createQuery("from dDetalleVta as d join fetch d.ventas as v join fetch v.operadores as o join fetch d.productos as p where o.idoperadores="+id+" and d.productos="+fact+" ORDER BY v.fecha DESC ").list();
         } finally{
          s.close();
             
         }
      
      
      
      }
       
    
}
