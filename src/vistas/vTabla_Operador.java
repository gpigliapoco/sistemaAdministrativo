/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.operadoresManager;
import datos.dOperadores;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import static vistas.vPrincipal.factory;
/**
 *
 * @author BeltariT
 */
public class vTabla_Operador extends javax.swing.JFrame {
    
//    SessionFactoryManager manager = new SessionFactoryManager();
//        SessionFactory factory = manager.getSessionFactory();

     
     dOperadores dts=new dOperadores();
     
     
     public  String ventana="ventas";
    
    public vTabla_Operador() {
        initComponents();
        mostrar();
    }

    public void mostrar(){
    
        operadoresManager funcOP=new operadoresManager(factory);
        
         DefaultTableModel model;

        List<dOperadores> lista = funcOP.listadoOPbyNombre(txtBuscar.getText());
        String[] titulos = {"ID", "NOMBRE"};
        model = new DefaultTableModel(null, titulos);
        
        Object data[] = new Object[2];
        for (dOperadores lista1 : lista) {
            data[0] = lista1.getIdoperadores();
            data[1] = lista1.getRazonSocial();
            
            model.addRow(data);
        }
        tableOperadores.setModel(model);
        tableOperadores.getColumnModel().getColumn(0).setMaxWidth(50);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOperadores = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableOperadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tableOperadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOperadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableOperadores);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda (2).png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPERADORES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableOperadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOperadoresMouseClicked
       if (evt.getClickCount() == 1) {
            int fila = tableOperadores.getSelectedRow();

            String cod = tableOperadores.getValueAt(fila, 0).toString();
            String nombre=tableOperadores.getValueAt(fila, 1).toString();
            
            operadoresManager funcOP=new operadoresManager(factory);
            Long cod1=Long.valueOf(cod);
            dts=funcOP.getOperadorById(cod1);
            
            
            if(ventana.equals("ventas")){
            vVentas.txtId.setText(cod);
            vVentas.txtRazon.setText(nombre);
            vVentas.txtDireccion.setText(dts.getDireccion());
            vVentas.txtLocalidad.setText(dts.getLocalidad());
            vVentas.txtIdVendedor.setText(dts.getVendedores().getIdVendedor().toString());
            vVentas.txtVendedor.setText(dts.getVendedores().getNombre());
            this.dispose();
            
            }
            if(ventana.equals("compra")){
            
                vCompras.txtId.setText(cod);
                vCompras.txtRazon.setText(nombre);
                vCompras.txtDireccion.setText(dts.getDireccion());
                vCompras.txtLocalidad.setText(dts.getLocalidad());
            
            
            this.dispose();
            }
            if(ventana.equals("cobros")){
            
                vCobrosYPagos.txtId.setText(cod);
                vCobrosYPagos.txtRazon.setText(nombre);
                vCobrosYPagos.txtDireccion.setText(dts.getDireccion());
                 
            
            
            this.dispose();
            }
            if(ventana.equals("tabla_factura")){
            
                vTabla_facturasVta.txtId.setText(cod);
                vTabla_facturasVta.txtRazon.setText(nombre);
                               
                        
            this.dispose();
            }
            if(ventana.equals("tabla_factura_cpr")){
            
                vTabla_facturasCpr.txtId.setText(cod);
                vTabla_facturasCpr.txtRazon.setText(nombre);
                               
                        
            this.dispose();
            }
             if(ventana.equals("tabla_factura_pago")){
            
                vTabla_facturasPago.txtId.setText(cod);
                vTabla_facturasPago.txtRazon.setText(nombre);
                               
                        
            this.dispose();
            }
             if(ventana.equals("ajustes")){
            
                vAjustes.txtId.setText(cod);
                vAjustes.txtNombre.setText(nombre);
                               
                        
            this.dispose();
            } 
             if(ventana.equals("cuentasCC")){
            
                vCuentasCC.txtId.setText(cod);
                vCuentasCC.txtOpeardor.setText(nombre);
                               
                        
            this.dispose();
            }  
             if(ventana.equals("report_ventas")){
            
                reportVTA1.txtIdop.setText(cod);
                reportVTA1.txtOperador.setText(nombre);
                               
                        
            this.dispose();
            }  
             if(ventana.equals("report_compras")){
            
                reportCPR.txtIdop.setText(cod);
                reportCPR.txtOperador.setText(nombre);
                               
                        
            this.dispose();
            }  
            
       }
    }//GEN-LAST:event_tableOperadoresMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        vOperadores form = new vOperadores();
        
        form.toFront();
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       mostrar();
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(vTabla_Operador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vTabla_Operador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vTabla_Operador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vTabla_Operador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vTabla_Operador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableOperadores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
