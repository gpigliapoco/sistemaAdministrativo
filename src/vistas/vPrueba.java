/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.ventasManager;
import conexion.SessionFactoryManager;
import datos.dVentas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;

/**
 *
 * @author BeltariT
 */
public class vPrueba extends javax.swing.JFrame {

   SessionFactoryManager manager = new SessionFactoryManager();
        SessionFactory factory = manager.getSessionFactory();
        
    public vPrueba() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtFecha = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jButton1)
                .addGap(60, 60, 60)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//       operadoresManager func=new operadoresManager();
//        dOperadores dts=func.getOperadorByData("o.idoperadores", 1L);
//        
//         List<dOperadores> list=func.listadoOPbyCampo("d","", "", "");
//        for (dOperadores list1 : list) {
//            System.out.println(list1.getRazonSocial());
//            System.out.println(list1.getCategoriaOP().getCategoria());
//            System.out.println(list1.getVendedores().getNombre());
//            
//        }
//        
//        for (dOperadores list1 : list) {
//            System.out.println(list1.getRazonSocial());
//        }
//         
//         for (dOperadores list1 : list) {
//             System.out.println(list1.getRazonSocial());
//             System.out.println(list1.getLocalidad());
//             System.out.println(list1.getCategoriaOP().getCategoria());
//             System.out.println("-------------------");
//        }
//        
        
//        cuentasManager func=new cuentasManager();
//        
//        dCuentasCorriente dts=func.getByIdcompras(1L);
//        System.out.println(dts.getObservacion());
//        
//        comprasManager func1=new comprasManager();
//        dCompras dts1=func1.getByIdcompras(1L);
//        System.out.println(dts1.getDescripcion());
        
//        cobrosYpagosManager func=new cobrosYpagosManager();
//        
//        List<ddetallecobros> list = func.getbyCPRxOPxFac(1L);
//        Object data[] = new Object[9];
//
//        for (ddetallecobros list1 : list) {
//            System.out.println(list1.getIddetalleCobro());
//            System.out.println(list1.getFormaPago().getIdFormaDePago().toString());
//            System.out.println(list1.getFormaPago().getFormaPago());
//            System.out.println(list1.getIdBanco().idBanco.toString());
//            System.out.println(list1.getBanco());
//            System.out.println(list1.getSucursal());
//            System.out.println(list1.getNumero());
//            System.out.println(list1.getVencimiento());
//            System.out.println(list1.getImporte());
//
//           
//
//        }
      
//        cuentasManager func=new cuentasManager();
//        
//        dCuentasCorriente dts=func.getSuma(1);
        dVentas dts=new dVentas();
        ventasManager funcc=new ventasManager(factory);
        
          

        List<dVentas> lista =funcc.getbyVTAxOPxFac(1l,1l); ///func.getbyVTAxOP(Long.valueOf(txtId.getText()));
     
    //Converting the String back to java.util.Date
   
        
        Object data[] = new Object[5];
        for (dVentas lista1 : lista) {
            data[0] = lista1.getIdVentas();
            data[1] = lista1.getComprobante();
            data[2] = lista1.getImporte();
            data[3] = lista1.getTipoComprobante().getComprobante();
            data[4] = lista1.getFecha();
              SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
      
             String strDate = sm.format(lista1.getFecha());
            try {
                //Converting the String back to java.util.Date
                Date dt = sm.parse(strDate);
            } catch (ParseException ex) {
                Logger.getLogger(vPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            System.out.println(lista1.getFecha());
            System.out.println(strDate);
           txtFecha.setText(strDate);
        }
      
       
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vPrueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}