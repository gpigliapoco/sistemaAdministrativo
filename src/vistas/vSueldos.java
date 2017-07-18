/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.empleadosManager;
import conexion.GenericRepository;
import datos.dCategoriaVALE;
import datos.dEmpleados;
import datos.dSueldo;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import static vistas.vPrincipal.factory;
/**
 *
 * @author leo
 */
public class vSueldos extends javax.swing.JInternalFrame {
//    SessionFactoryManager manager = new SessionFactoryManager();
//        SessionFactory factory = manager.getSessionFactory();

//    GenericRepository1<dEmpleados,Long> funcEMPL=new GenericRepository1<dEmpleados, Long>(factory) {
//        };
//    GenericRepository1<dCategoriaVALE,Long> funcVALE=new GenericRepository1<dCategoriaVALE, Long>(factory) {
//        };
//    GenericRepository1<dSueldo,Long> func=new GenericRepository1<dSueldo, Long>(factory) {
//        };
//    sueldosManager funcSU=new sueldosManager(factory);
    
    dSueldo dts=new  dSueldo();
//    dEmpleados dtsEMPL=new dEmpleados();
//    dCategoriaVALE dtsVALE=new dCategoriaVALE();
    
    java.util.Date dato = null;
    
    
    public vSueldos() {
        initComponents();
    }

    public void habilitar(){
    
        txtFecha.setText("");
        txtIdemple.setText("");
        txtNombre.setText("");
        txtIdvale.setText("");
        txtVale.setText("");
        txtImporte.setText("");
        
        txtFecha.setEnabled(true);
        txtIdemple.setEnabled(true);
        txtNombre.setEnabled(false);
        txtIdvale.setEnabled(true);
        txtVale.setEnabled(false);
        txtImporte.setEnabled(true);
        
        
    
    }
    
    public void inHabilitar(){
    
      
        
        txtFecha.setEnabled(false);
        txtIdemple.setEnabled(false);
        txtNombre.setEnabled(false);
        txtIdvale.setEnabled(false);
        txtVale.setEnabled(false);
        txtImporte.setEnabled(false);
        
      
    }
    
    public void guardar(){
         if (txtIdemple.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA NOMBRE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtIdemple.requestFocus();
            
            return ;
            
        }
        if (txtIdvale.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA COMPROBANTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtIdvale.requestFocus();
            
            return ;
            
        }
        if (txtImporte.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA IMPORTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtImporte.requestFocus();
            
            return ;
            
        }
       
        
       GenericRepository<dSueldo,Long> func=new GenericRepository<dSueldo, Long>(factory) {
       };
        
         GenericRepository<dEmpleados,Long> funcEMPL=new GenericRepository<dEmpleados, Long>(factory) {
        };
    GenericRepository<dCategoriaVALE,Long> funcVALE=new GenericRepository<dCategoriaVALE, Long>(factory) {
        };
    
        obtenerFecha();
        
        dEmpleados dtsEMPL=funcEMPL.getById(Long.valueOf(txtIdemple.getText()));
        dCategoriaVALE dtsVALE=funcVALE.getById(Long.valueOf(txtIdvale.getText()));
        
        dts.setFecha(dato);
        dts.setEmpleados(dtsEMPL);
        dts.setCategoriaVALE(dtsVALE);
        dts.setImporte(Double.valueOf(txtImporte.getText()));
        dts.setObservacion(txtObservacion.getText());
        func.save(dts);
        
        inHabilitar();
        habilitar();
    
    }
    
    public void obtenerFecha() {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");  ////// metodo para convertir string a date y colocarlo en jdatechooser   http://www.forosdelweb.com/f45/mysql-java-netbeans-java-sql-date-860869/
        String fecha = txtFecha.getText();

        try {

            dato = formatoDelTexto.parse(fecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

    }
    
//    public void mostrar(){
//    
//        
//    
//     DefaultTableModel model;
//
//        List<dSueldo> lista = funcSU.getOperadorSueldo();
//        String[] titulos = {"ID", "NOMBRE", "COMPROBANTE","IMPORTE"};
//        model = new DefaultTableModel(null, titulos);
//        
//        Object data[] = new Object[4];
//        for (dSueldo lista1 : lista) {
//            data[0] = lista1.getIdSueldo().toString();
//            data[1] = lista1.getEmpleados().getNombre();
//            data[2] = lista1.getCategoriaVALE().getCategoria();
//            data[3] = lista1.getImporte();
//            model.addRow(data);
//        }
//        tableVales.setModel(model);
//        tableVales.getColumnModel().getColumn(0).setMaxWidth(50);
//        tableVales.getColumnModel().getColumn(1).setMaxWidth(400);
//        tableVales.getColumnModel().getColumn(2).setMaxWidth(200);
//        tableVales.getColumnModel().getColumn(3).setMaxWidth(300);
//    
//    
//    
//    
//    
//    
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdemple = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtVale = new javax.swing.JTextField();
        txtIdvale = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        btnGuarda = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("JORNALES EMPLEADOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ID");

        txtIdemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdempleActionPerformed(evt);
            }
        });
        txtIdemple.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdempleKeyPressed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtVale.setEditable(false);
        txtVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValeActionPerformed(evt);
            }
        });

        txtIdvale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdvaleActionPerformed(evt);
            }
        });
        txtIdvale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdvaleKeyPressed(evt);
            }
        });

        jLabel2.setText("Comprobante");

        txtImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteActionPerformed(evt);
            }
        });

        jLabel3.setText("importe");

        jLabel4.setText("Fecha");

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        btnGuarda.setText("CARGAR");
        btnGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaActionPerformed(evt);
            }
        });

        jLabel7.setText("Observacion");

        txtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtIdvale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVale, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtIdemple, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuarda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdvale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnGuarda)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdempleActionPerformed
        empleadosManager funcOPP=new empleadosManager(factory);
        dEmpleados op=funcOPP.getOperadorById(Long.valueOf(txtIdemple.getText()));
        
        if(op!=null){
        txtNombre.setText(op.getNombre());
        
        txtIdemple.transferFocus();
        }else{
        JOptionPane.showMessageDialog(null,"no exite empleado");
        return;
        }
    }//GEN-LAST:event_txtIdempleActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
       txtNombre.transferFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValeActionPerformed
        txtVale.transferFocus();
    }//GEN-LAST:event_txtValeActionPerformed

    private void txtIdvaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdvaleActionPerformed
       GenericRepository<dCategoriaVALE,Long> funcVALE=new GenericRepository<dCategoriaVALE, Long>(factory) {
        };
       dCategoriaVALE cate=funcVALE.getById(Long.valueOf(txtIdvale.getText()));
       
       if(cate!=null){
        txtVale.setText(cate.getCategoria());
        txtObservacion.setText(cate.getCategoria());
        txtIdvale.transferFocus();
       }else{
       JOptionPane.showMessageDialog(null,"no exite comprobante");
        return;
       }
    }//GEN-LAST:event_txtIdvaleActionPerformed

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
       txtFecha.transferFocus();
    }//GEN-LAST:event_txtFechaActionPerformed

    private void btnGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaActionPerformed
      guardar();
    }//GEN-LAST:event_btnGuardaActionPerformed

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
      txtObservacion.transferFocus();
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void txtIdempleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdempleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_empleados form = new vTabla_empleados();
            form.ventana = "sueldos";
            form.toFront();
            form.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txtIdempleKeyPressed

    private void txtIdvaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdvaleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_Vales form = new vTabla_Vales();
            form.ventana = "sueldos";
            form.toFront();
            form.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txtIdvaleKeyPressed

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
            java.util.logging.Logger.getLogger(vSueldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vSueldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vSueldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vSueldos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vSueldos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuarda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField txtFecha;
    protected static javax.swing.JTextField txtIdemple;
    protected static javax.swing.JTextField txtIdvale;
    private javax.swing.JTextField txtImporte;
    protected static javax.swing.JTextField txtNombre;
    protected static javax.swing.JTextField txtObservacion;
    protected static javax.swing.JTextField txtVale;
    // End of variables declaration//GEN-END:variables
}
