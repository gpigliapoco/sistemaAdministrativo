/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import conexion.SessionManager;
import datos.dCheques;
import datos.ddetallecobros;
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
public class chequesManager {
    
      private SessionFactory factory;

    public chequesManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
   
    public List<ddetallecobros> getbyId(String suc,String numero,String banco,String importe,String fecha,String operador) {
        Session s = factory.openSession();

        try {
            return   s.createQuery("From ddetallecobros as d join fetch d.idBanco as b join fetch d.cobros as c join fetch c.operadores as o where b.idBanco"+banco+" and d.Sucursal"+suc+" and d.numero"+numero+" and d.importe"+importe+" and d.vencimiento"+fecha+" and o.idoperadores"+operador+"  ").
                    
                //    setParameter("numero", numero).
                   // setParameter("banco",banco ).
              //      setParameter("importe", importe).
                  list();
          
             

        } catch (HibernateException ex) {
            throw ex;
        }finally{
              s.close();
        }

    }
    
     public List<ddetallecobros> listadoOPbyCampo(int suc,int numero,String banco,int importe,String fecha){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(ddetallecobros.class).
                   setFetchMode("cobros", FetchMode.JOIN).
                  
                   add(Restrictions.like("Sucursal",suc)).
            add(Restrictions.like("numero",numero)).
            add(Restrictions.like("banco",banco)).
            add(Restrictions.like("importe",importe)).
            add(Restrictions.like("vencimiento",fecha)).        
            list();
         } finally{
          s.close();
             
         }
      
      
      
      }
    
    
}
