/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.chequesManager;
import Controllers.cobrosYpagosManager;
import Controllers.cuentasManager;
import conexion.GenericRepository;
import conexion.SessionFactoryManager;
import datos.dBanco;
import datos.dCheques;
import datos.dCobros;
import datos.dCuentasCorriente;
import datos.dFormaDePago;
import datos.dOperadores;
import datos.ddetallecobros;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.SessionFactory;
import static vistas.vPrincipal.factory;

public class vEditorCobros extends javax.swing.JInternalFrame {
    
////      SessionFactoryManager manager = new SessionFactoryManager();
////        SessionFactory factory = manager.getSessionFactory();


//    GenericRepository<dCobros, Long> funcCOB = new GenericRepository<dCobros, Long>(factory) {
//    };
//    GenericRepository<ddetallecobros, Long> funcDTA = new GenericRepository<ddetallecobros, Long>(factory) {
//    };
//         GenericRepository<dCuentasCorriente, Long> funcCC = new GenericRepository<dCuentasCorriente, Long>(factory) {
//    };

//    cobrosYpagosManager func = new cobrosYpagosManager(factory);
    public static long cod1;

    dCobros dts = new dCobros();
//    dCheques cheque = new dCheques();
//    dFormaDePago formPago = new dFormaDePago();
//    ddetallecobros detalle = new ddetallecobros();
//    dOperadores dtsOpera = new dOperadores();
//
//    ddetallecobros dtsCOB = new ddetallecobros();
//    dCuentasCorriente cuenta =new dCuentasCorriente();

    java.util.Date dato = null;

    public vEditorCobros() {
        initComponents();
    }
    
    

    public void habilitar(){
        
        txtId.setText("");
        txtRazon.setText("");
        txtObservacion.setText("");
        txtTipoComprobante.setText("");
        txtIdCobro.setText("");
        txtNumero.setText("");
        txtTotal.setText("");
        limpiaTabla();
    
    }
    
    
    public void mostar() {

        DefaultTableModel model;
        
         cobrosYpagosManager func = new cobrosYpagosManager(factory);

         dCobros dtsCOB = func.getbyDetalleCobro(Long.valueOf(txtIdCobro.getText()));
//        ddetallecobros dtsCOB1 = func.getbyDetalleEFECT(Long.valueOf(txtIdCobro.getText()));

        txtNumero.setText(dtsCOB.getComprobante());
        txtObservacion.setText(dtsCOB.getDescripcion());

        String[] titulos = {"COD","ID", "TIPO", "ID", "BANCO", "SUC", "NUMERO", "FECHA.VTO", "IMPORTE"};
        model = new DefaultTableModel(null, titulos);

        
          List<ddetallecobros> list = func.getbyCPRxOPxFac(Long.valueOf(txtIdCobro.getText()));
        Object data[] = new Object[9];

        for (ddetallecobros list1 : list) {
            data[0] = list1.getIddetalleCobro();
            data[1] = list1.getFormaPago().getIdFormaDePago().toString();
            data[2] = list1.getFormaPago().getFormaPago();
            data[3] = list1.getIdBanco().idBanco.toString();
            data[4] = list1.getBanco();
            data[5] = list1.getSucursal();
            data[6] = list1.getNumero();
            
            data[8] = list1.getImporte();

            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
      
             String strDate = sm.format(list1.getVencimiento());
            try {
                //Converting the String back to java.util.Date
                Date dt = sm.parse(strDate);
            } catch (ParseException ex) {
                Logger.getLogger(vPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            data[7] = strDate;
            
            model.addRow(data);

        }

        tablePagos.setModel(model);
        tablePagos.getColumnModel().getColumn(0).setMaxWidth(50);
        tablePagos.getColumnModel().getColumn(1).setMaxWidth(50);
        tablePagos.getColumnModel().getColumn(2).setMaxWidth(150);
        tablePagos.getColumnModel().getColumn(3).setMaxWidth(50);
        tablePagos.getColumnModel().getColumn(4).setMaxWidth(150);
        tablePagos.getColumnModel().getColumn(5).setMaxWidth(50);
        tablePagos.getColumnModel().getColumn(6).setMaxWidth(200);
        tablePagos.getColumnModel().getColumn(7).setMaxWidth(200);
        tablePagos.getColumnModel().getColumn(8).setMaxWidth(200);
        
        suma();

    }

    public void suma() {

        double valor = 0;

        int filas = tablePagos.getRowCount();

        for (int i = 0; i < filas; i++) {

            String tol = String.valueOf(tablePagos.getValueAt(i, 8));

            double total = Double.parseDouble(tol);

            valor = valor + total;

        }

        txtTotal.setText(String.valueOf(valor));

    }

    public void guarda() {

        GenericRepository<dOperadores, Long> funcOP = new GenericRepository<dOperadores, Long>(factory) {
        };
                 GenericRepository<dCuentasCorriente, Long> funcCC = new GenericRepository<dCuentasCorriente, Long>(factory) {
    };
         GenericRepository<dCobros, Long> funcCOB = new GenericRepository<dCobros, Long>(factory) {
    };
        
        cuentasManager funcCCID=new cuentasManager(factory);
        dCuentasCorriente dtsCC=funcCCID.getByIdcobros(cod1);
        obtenerFecha();

       dOperadores dtsOpera = funcOP.getById(Long.valueOf(txtId.getText()));
        
        dts.setOperadores(dtsOpera);
        dts.setComprobante(txtNumero.getText());
        dts.setFecha(dato);
        dts.setImporte(Double.valueOf(txtTotal.getText()));
        dts.setDescripcion(txtObservacion.getText());
        dts.setTipoComprobante(txtTipoComprobante.getText());
        dts.setIdcobros(Long.valueOf(txtIdCobro.getText()));
        funcCOB.update(dts);
        
        dCuentasCorriente cuenta=new dCuentasCorriente();
        
          if (txtTipoComprobante.getText().equals("COBRO")) {
              cuenta.setHaber(Double.valueOf(txtTotal.getText()));
              cuenta.setDebe(0.0);
          }
          if (txtTipoComprobante.getText().equals("PAGO")) {
              cuenta.setDebe(Double.valueOf(txtTotal.getText()));
              cuenta.setHaber(0.0);
          }
        cuenta.setFecha(dato);
        cuenta.setObservacion(txtObservacion.getText());
        cuenta.setOperadores(dtsOpera);
        cuenta.setCobros(dts);
        cuenta.setIdCuentas(dtsCC.getIdCuentas());
        cuenta.setNumeroID(Integer.parseInt(txtIdCobro.getText()));
        funcCC.update(cuenta);
        
        guardarDETALLE();
        habilitar();
    }

    public void guardarDETALLE() {

        GenericRepository<dBanco, Long> funcBCO = new GenericRepository<dBanco, Long>(factory) {
        };

        GenericRepository<dFormaDePago, Long> funcFO = new GenericRepository<dFormaDePago, Long>(factory) {
        };
        GenericRepository<dCheques, Long> funcCH = new GenericRepository<dCheques, Long>(factory) {
        };
         GenericRepository<ddetallecobros, Long> funcDTA = new GenericRepository<ddetallecobros, Long>(factory) {
    };
        dBanco banco = new dBanco();

        int fila = tablePagos.getRowCount();
        for (int x = 0; x < fila; x++) {

            String codPago = String.valueOf(tablePagos.getValueAt(x, 1));
            String codBco = String.valueOf(tablePagos.getValueAt(x, 3));
            String bco = String.valueOf(tablePagos.getValueAt(x, 4));
            String suc = String.valueOf(tablePagos.getValueAt(x, 5));
            String nCH = String.valueOf(tablePagos.getValueAt(x, 6));
            String fchVTO = String.valueOf(tablePagos.getValueAt(x, 7));
            String Importe = String.valueOf(tablePagos.getValueAt(x, 8));
            String formaPagos = String.valueOf(tablePagos.getValueAt(x, 2));
            String codDet = String.valueOf(tablePagos.getValueAt(x, 0));

            dFormaDePago formPago = funcFO.getById(Long.valueOf(codPago));
            Long codDetalle=Long.valueOf(codDet);
            
            ddetallecobros detalle = new ddetallecobros();
            
            detalle.setCobros(dts);
            detalle.setFormaPago(formPago);
            
            
            

            if (formaPagos.equals("CHEQUE PROPIO") || formaPagos.equals("CHEQUE")) {

                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");  ////// metodo para convertir string a date y colocarlo en jdatechooser   http://www.forosdelweb.com/f45/mysql-java-netbeans-java-sql-date-860869/
                String fecha = fchVTO;
                java.util.Date datoVTO = null;

                try {
                    datoVTO = formatoDelTexto.parse(fecha);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                banco = funcBCO.getById(Long.valueOf(codBco));
                
                
                detalle.setBanco(bco);

                detalle.setCategoria(formaPagos);
                detalle.setIdBanco(banco);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setNumero(Integer.parseInt(nCH));
                detalle.setSucursal(Integer.parseInt(suc));
                detalle.setVencimiento(datoVTO);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setIddetalleCobro(codDetalle);
                funcDTA.update(detalle);
            }

            if (formaPagos.equals("EFECTIVO")) {
                
                banco = funcBCO.getById(1l);
                
                detalle.setCategoria(formaPagos);
                detalle.setVencimiento(dato);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setSucursal(0);
                detalle.setNumero(0);
                detalle.setBanco("----");
                detalle.setIdBanco(banco);
                detalle.setIddetalleCobro(codDetalle);
                funcDTA.update(detalle);
            }

            if (formaPagos.equals("DEPOSITO")) {
                
                banco = funcBCO.getById(1l);

                detalle.setBanco(bco);
                detalle.setIdBanco(banco);
                detalle.setSucursal(0);
                detalle.setVencimiento(dato);
                detalle.setCategoria(formaPagos);
                
                detalle.setNumero(0);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setIddetalleCobro(codDetalle);
                funcDTA.update(detalle);
            }
        }
    }

    public void obtenerFecha() {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");  ////// metodo para convertir string a date y colocarlo en jdatechooser   http://www.forosdelweb.com/f45/mysql-java-netbeans-java-sql-date-860869/
        String fecha = txtFecha.getText();

        try {

            dato = formatoDelTexto.parse(fecha);
            ;
        } catch (ParseException ex) {

            ex.printStackTrace();

        }

    }
    
     public void limpiaTabla() { /// METODO LIMPIAR TABLA (NO FUNCIONA)
        try {
            DefaultTableModel temp = (DefaultTableModel) tablePagos.getModel();
            int a = temp.getRowCount();
            for (int i = 0; i < a; i++) {
                temp.removeRow(0); //aquí estaba el error, antes pasaba la i como parametro.... soy un bacín  XD
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtRazon = new javax.swing.JTextField();
        txtObservacion = new javax.swing.JTextField();
        txtTipoComprobante = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        cboTipoComprobante = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtIdCobro = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePagos = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setTitle("EDITOR DE COBROS Y PAGOS");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Formulario De Edicion Cobros y Pagos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Id Cliente");

        jLabel2.setText("Fecha");

        jLabel6.setText("Tipo Comprobante");

        jLabel7.setText("Numero");

        jLabel8.setText("Observacion");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
        });

        txtRazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonActionPerformed(evt);
            }
        });

        txtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionActionPerformed(evt);
            }
        });

        txtTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoComprobanteActionPerformed(evt);
            }
        });

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        cboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COBRO", "PAGO" }));
        cboTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoComprobanteActionPerformed(evt);
            }
        });

        jLabel3.setText("Id.Operacion");

        txtIdCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCobroActionPerformed(evt);
            }
        });
        txtIdCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdCobroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtIdCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablePagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "ID", "TIPO", "ID", "BANCO", "SUCURSAL", "NUMERO", "FECHA.VTO", "IMPORTE"
            }
        ));
        jScrollPane1.setViewportView(tablePagos);

        jLabel17.setText("TOTAL");

        btnGuardar.setText("EDITAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("TRAER FACTURA");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnNuevo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(btnEliminar))))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 82, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        txtId.transferFocus();
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_facturasPago form = new vTabla_facturasPago();
            form.toFront();
            form.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtRazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonActionPerformed
        txtRazon.transferFocus();
    }//GEN-LAST:event_txtRazonActionPerformed

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
        txtObservacion.transferFocus();
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void txtTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoComprobanteActionPerformed
        txtTipoComprobante.transferFocus();
    }//GEN-LAST:event_txtTipoComprobanteActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        txtNumero.transferFocus();
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        txtFecha.transferFocus();
    }//GEN-LAST:event_txtFechaActionPerformed

    private void cboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoComprobanteActionPerformed
        txtTipoComprobante.setText(cboTipoComprobante.getSelectedItem().toString());
    }//GEN-LAST:event_cboTipoComprobanteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      guarda();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GenericRepository<dCobros, Long> funcCOB = new GenericRepository<dCobros, Long>(factory) {
    };
        dCobros cobro=funcCOB.getById(cod1);
        
        funcCOB.delete(cobro);
        JOptionPane.showMessageDialog(rootPane, "COMPROBANTE ELIMINADO");
        limpiaTabla();
        habilitar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tablePagos.getModel(); //TableProducto es el nombre de mi tabla ;)
       int fila = tablePagos.getSelectedRow();
        String codigo=tablePagos.getValueAt(fila, 0).toString();
         GenericRepository<ddetallecobros, Long> funcDTA = new GenericRepository<ddetallecobros, Long>(factory) {
    };
        System.out.println(codigo);
        
       ddetallecobros dtsEliminar=funcDTA.getById(Long.valueOf(codigo));
       funcDTA.delete(dtsEliminar);
        
        dtm.removeRow(tablePagos.getSelectedRow());
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     mostar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIdCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCobroActionPerformed

    private void txtIdCobroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCobroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCobroKeyPressed

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
            java.util.logging.Logger.getLogger(vEditorCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vEditorCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vEditorCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vEditorCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vEditorCobros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cboTipoComprobante;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePagos;
    public static javax.swing.JTextField txtFecha;
    protected static javax.swing.JTextField txtId;
    protected static javax.swing.JTextField txtIdCobro;
    protected static javax.swing.JTextField txtNumero;
    protected static javax.swing.JTextField txtObservacion;
    protected static javax.swing.JTextField txtRazon;
    protected static javax.swing.JTextField txtTipoComprobante;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
