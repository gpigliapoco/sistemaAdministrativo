/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dEmpleados;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leo
 */
public class empleadosManager {
    
      private SessionFactory factory;

    public empleadosManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public List<dEmpleados> listadoEMP(){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dEmpleados.class).
                   
                   setFetchMode("categoria", FetchMode.JOIN)
            .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
      public dEmpleados getOperadorById(Long id){
     
         Session s = factory.openSession();
         try {
             return (dEmpleados) s.createQuery("From dEmpleados as o join fetch o.categoria  where o.idEmpleados= :id").setParameter("id", id).uniqueResult();
         } finally{
          s.close();
             
         }
     
     
     }
       public List<dEmpleados> listadoOPbyNombre(String data){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dEmpleados.class).
             add(Restrictions.like("nombre", "%"+data+"%"))
               .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
}
