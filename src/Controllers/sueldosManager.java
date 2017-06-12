/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dSueldo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author leo
 */
public class sueldosManager {
    
      private SessionFactory factory;

    public sueldosManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dSueldo getbyId(Long id){
    
        Session s=factory.openSession();
    try{
         
          return (dSueldo) s.createQuery("from dSueldo as s join fetch s.empleados join fecth o.categoria where idoperadores= :id").setParameter("id", id).uniqueResult();
                 
          
         }finally{
          s.close();
        }
    
    }
     public List<dSueldo> getOperadorSueldo(){
     
         Session s = factory.openSession();
         try {
             return s.createQuery("From dOperadores o join fetch o.categoriaOP join fetch o.vendedores join fetch o.transporte").list();
         } finally{
          s.close();
             
         }
     
     
     }
    
}
