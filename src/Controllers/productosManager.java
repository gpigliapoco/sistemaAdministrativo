/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import datos.dProductos;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author mike
 */
public class productosManager {
    
    private SessionFactory factory;

    public productosManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public List<dProductos> getProductos(){
     
         Session s = factory.openSession();
         try {
             return s.createQuery("From dProductos as p order by p.productos ").list();
         } finally{
          s.close();
             
         }
     
     
     }
}
