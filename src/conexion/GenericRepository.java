/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Marcelo
 * @param <E>
 */
public abstract class GenericRepository <E,PK extends Serializable>{

    private SessionFactory factory;

    public GenericRepository(SessionFactory factory) {
        if (factory == null) {
            throw new RuntimeException("factory is mandatory");
        }
        this.factory = factory;
    }

    public PK save(E entity) {
        Session s = factory.openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            PK id = (PK) s.save(entity);
            tx.commit();
            return id;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            
            s.close();
            
        }
    }

    public E update(E entity) {
        Session s = factory.openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(entity);
            tx.commit();
            return entity;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            s.close();
        }
    }

    public void delete(E entity) {
        Session s = factory.openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.delete(entity);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            s.close();
        }
    }
    
    public Class getType(){
       Class clazz = this.getClass(); //PersonaRepository
       Type superClass = clazz.getGenericSuperclass();//GenericRepository
       ParameterizedType parameterizedType = (ParameterizedType) superClass; //GenericRepository <E,PK extends Serializable>
       Type[] generics = parameterizedType.getActualTypeArguments();//<E,PK extends Serializable>
       Class entity =(Class) generics[0];// E
       return entity;
    }
    
      public List<E> getAll(){
         Session s = factory.openSession();
         try{
           return s.createCriteria(getType()).list();
         }finally{
             s.flush();
          s.close();
             
          
        }
    } 
     public E getById (PK id){
        Session s =factory.openSession();
         try{
          E entity = (E) s.get(getType(),id);
          return entity;
         }finally{
             s.flush();
          s.close();
          
        }
    }  
      public E getByIdExist (PK id){
        Session s =factory.openSession();
         try{
          E entity = (E) s.get(getType(),id);
          if(entity != null){
              return entity;
          }else
          {
              JOptionPane.showMessageDialog(null,"no es valido");
             return null;
          }
          
         }finally{
             s.flush();
          s.close();
          
        }
    }  
//       public ArrayList<E> getAll1() {
//
//        Session s = SessionManager.getSession();
//        try {
//
//            ArrayList<E> arreglo = new ArrayList<>();
//            List<E> lista = s.createQuery("from " + e.getName() ).list();
//            Iterator<E> iter = lista.iterator();
//
//            while (iter.hasNext()) {
//                System.out.println("hay" + lista.size());
//                E noti = (E) iter.next();
//                arreglo.add(noti);
//            }
//            System.out.println("hay" + lista.size());
//
//            return arreglo;
//
//        } catch (HibernateException ex) {
//            throw ex;
//
//        } finally {
//            s.flush();
//            s.close();
//             
//        }
//
//    }
}
