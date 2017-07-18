/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author BeltariT
 */
public class conexcion1 {
//   public String db="fabrica";
//   /// public String url="jdbc:sqlserver://LEO:1433;databaseName=fabrica";
 // public String url="jdbc:sqlserver://192.168.100.105:1433;databaseName=fabrica";
//    public String user="sa";
//   public String pass="sa";
//    Connection conex=null;
    
       public String db="orestes_2";
    public String url="jdbc:mysql://192.168.100.103:3306/"+db;
    public String user="root";
    public String pass="";
    
    
    public conexcion1(){
    }
    
    public Connection conectar1(){
        
//        
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conex=DriverManager.getConnection(url,user,pass);
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showConfirmDialog(null,e);
//        }
//        
//        return conex;
          Connection link=null;
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link=DriverManager.getConnection(this.url,this.user,this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null,e);
            System.out.println(e);
        }
        
        return link;
    
    
    }
    
       
    
}

