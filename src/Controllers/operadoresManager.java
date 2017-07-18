/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import datos.dOperadores;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BeltariT
 */
public class operadoresManager {
    
      private SessionFactory factory;

    public operadoresManager(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }
    
     public dOperadores getByIdop (Long id){
        Session s = factory.openSession();
         try{
         
          return (dOperadores) s.createQuery("from dOperadores where idoperadores= :id").setParameter("id", id).uniqueResult();
                 
          
         }finally{
          s.close();
        }
    }  
     public List<dOperadores> getOperador(){
     
         Session s = factory.openSession();
         try {
             return s.createQuery("From dOperadores o join fetch o.categoriaOP join fetch o.vendedores join fetch o.transporte").list();
         } finally{
          s.close();
             
         }
     
     
     }
     
      public dOperadores getOperadorById(Long id){
     
         Session s = factory.openSession();
         try {
             return (dOperadores) s.createQuery("From dOperadores as o join fetch o.categoriaOP join fetch o.vendedores join fetch o.transporte where o.idoperadores= :id").setParameter("id", id).uniqueResult();
         } finally{
          s.close();
             
         }
     
     
     }
      
      public dOperadores getOperadorByData(String campo,Long dato){
     
         Session s = factory.openSession();
         try {
             return (dOperadores) s.createQuery("From dOperadores as o join fetch o.categoriaOP join fetch o.vendedores join fetch o.transporte where "+campo+"= :id").setParameter("id", dato).uniqueResult();
         } finally{
          s.close();
             
         }
     
     
     }
      
     
       
      public List<dOperadores> listadoOP(){
      Session s = factory.openSession();
         try {
             return  s.createCriteria(dOperadores.class).add(Restrictions.eq("CLIENTES", s)).
                     setFetchMode("categoriaOP", FetchMode.JOIN).createAlias("categoriaOP", "c").
                     setFetchMode("vendedores", FetchMode.JOIN).
                     addOrder(Order.desc("c.categoria")).list();
         } finally{
          s.close();
             
         }
      
      
      
      }
      
       public List<dOperadores> listadoOPbyCampo(String data,String data1,String data2,String data3){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dOperadores.class).
             add(Restrictions.like("razonSocial", "%"+data+"%")).
             add(Restrictions.like("direccion", "%"+data1+"%")).
             add(Restrictions.like("localidad", data2+"%")).
             add(Restrictions.like("provincia", data3+"%"))      
            .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
       
        public List<dOperadores> listadoOPbyNombre(String data){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dOperadores.class).
             add(Restrictions.like("razonSocial", "%"+data+"%"))
               .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
       
       public List<dOperadores> listadoOPordenadoByCampo(String campo){
      Session s = factory.openSession();
         try {
           return  s.createCriteria(dOperadores.class).
                   addOrder(Order.asc(campo)).
                   setFetchMode("categoriaOP", FetchMode.JOIN)
            .list();
         } finally{
          s.close();
             
         }
      
      
      
      }
}
