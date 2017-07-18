/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dPorcentajeComi;
import datos.dProductos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author BeltariT
 */
public class porcentajeComiManager {
    
      private SessionFactory factory;

    public porcentajeComiManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public  List<dPorcentajeComi> getall (){
        Session s = factory.openSession();
         try{
         
          return s.createQuery("FROM dPorcentajeComi c join fetch c.idVendedor join fetch c.idCategoriaPR     ").list();
                 
          
         }finally{
          s.close();
        }
    }
     public Double getComisionProducto(int id,int pr){
 Session s = factory.openSession();

        try {
            
           Double comision= (Double) s.createSQLQuery("select comisionPorc from porcentajeComi where fk_idVendedores= :id and fk_idCategoriaPR= :pr").
                   setParameter("id", id).
                   setParameter("pr", pr).
                   uniqueResult();
            
            
            return comision;
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }




}
public Long getProductosCategoria(Long id){
 Session s = factory.openSession();

        try {
            
           dProductos prr=(dProductos) s.createQuery("FROM dProductos c join fetch c.categoria v where idProductos= :id").setParameter("id", id).uniqueResult();
          
           Long comi=prr.getCategoria().getIdCategoriaPR();
            
            
            return comi;

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }




}

    
}
