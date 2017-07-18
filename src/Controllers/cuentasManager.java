/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dCuentasCorriente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import static vistas.vPrincipal.cn;

/**
 *
 * @author BeltariT
 */
public class cuentasManager {
    
    public int ultimoId;
      private SessionFactory factory;

    public cuentasManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
    public dCuentasCorriente getByIdcompras(Long dato){
    
        Session s = factory.openSession();
         try {
             return  (dCuentasCorriente) s.createQuery("From dCuentasCorriente as c join fetch c.compras s  where s.idcompras= :id").setParameter("id", dato).uniqueResult();
         } finally{
          s.close();
             
         }
  
    
    }
     public dCuentasCorriente getByIdventas(Long dato){
    
        Session s = factory.openSession();
         try {
             return  (dCuentasCorriente) s.createQuery("From dCuentasCorriente as c join fetch c.ventas s  where s.idVentas= :id").setParameter("id", dato).uniqueResult();
         } finally{
          s.close();
             
         }
  
    
    }
    
     public dCuentasCorriente getByIdcobros(Long dato){
    
        Session s = factory.openSession();
         try {
             return  (dCuentasCorriente) s.createQuery("From dCuentasCorriente as c join fetch c.cobros s  where s.idcobros= :id").setParameter("id", dato).uniqueResult();
         } finally{
          s.close();
             
         }
  
    
    }
       public double getSuma(int dato){
    
            double saldo=0.0;
        Session s = factory.openSession();
         try {
            
        Query query= s.createQuery("select sum(c.debe-c.haber)as suma from dCuentasCorriente as c where c.operadores="+dato+"");
         query.setCacheable(true);
          final Object obj = query.uniqueResult();
          saldo=  (double) obj;
             System.out.println(saldo);
           return  saldo;
              
         } finally{
          s.close();
             
         }
       }
       
        public dCuentasCorriente getByIdAjuste(Long dato,int id){
    
        Session s = factory.openSession();
         try {
             return  (dCuentasCorriente) s.createQuery("From dCuentasCorriente as c join fetch c.operadores where c.operadores="+dato+" and c.idCuentas="+id+"").uniqueResult();
         } finally{
          s.close();
             
         }
  
    
    }
          public void ultimoId() {

       String sSQL = "select max(idCuentas) as id from cuentacorriente ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                ultimoId= rs.getInt("id")+1;
                 System.out.println(ultimoId);
            }
          

        } catch (SQLException ex) {
            Logger.getLogger(dCuentasCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{ 
            
            
        }

    }
}
