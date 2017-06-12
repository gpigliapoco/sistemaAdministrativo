/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dTransporte;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mike
 */
public class transporteManager {
    
      private SessionFactory factory;

    public transporteManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public List<dTransporte> listadoOPbyNombre(String data){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dTransporte.class).
             add(Restrictions.like("nombre", "%"+data+"%"))
               .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
}
