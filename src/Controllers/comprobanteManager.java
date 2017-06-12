/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dTipoComprobante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author leo
 */
public class comprobanteManager {
    
      private SessionFactory factory;

    public comprobanteManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public dTipoComprobante getComprobanteById(Long id){
     
         Session s = factory.openSession();
         try {
             return (dTipoComprobante) s.createQuery("From dTipoComprobante as o where o.idTipo_comprobante= :id").setParameter("id", id).uniqueResult();
         } finally{
          s.close();
             
         }
     
     
     }
}
