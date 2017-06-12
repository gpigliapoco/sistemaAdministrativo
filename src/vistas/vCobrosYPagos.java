/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.SessionFactory;
import static vistas.vPrincipal.factory;
/**
 *
 * @author BeltariT
 */
public class vCobrosYPagos extends javax.swing.JInternalFrame {
    
//     SessionFactoryManager manager = new SessionFactoryManager();
//        SessionFactory factory = manager.getSessionFactory();

    


//    GenericRepository<dCobros, Long> func = new GenericRepository<dCobros, Long>() {
//    };
//    GenericRepository<ddetallecobros, Long> funcDTA = new GenericRepository<ddetallecobros, Long>() {
//    };
//    GenericRepository<dOperadores, Long> funcOP = new GenericRepository<dOperadores, Long>() {
//    };
//    GenericRepository<dFormaDePago, Long> funcFO = new GenericRepository<dFormaDePago, Long>() {
//    };
//    GenericRepository<dBanco, Long> funcBCO = new GenericRepository<dBanco, Long>() {
//    };

    dCobros dts = new dCobros();
//    dCheques cheque = new dCheques();
//    dFormaDePago formPago = new dFormaDePago();
//    ddetallecobros detalle = new ddetallecobros();
//    dOperadores dtsOpera = new dOperadores();

    java.util.Date dato = null;

    public vCobrosYPagos() {
        initComponents();
        inHabilitar();
        inHabilitarFORMAS();
    }

    public void habilitar() {

        txtId.setText("");
        txtRazon.setText("");
        txtDireccion.setText("");
        txtTipoComprobante.setText("");
        txtNumero.setText("");
        txtObservacion.setText("");
        txtFecha.setText("");

        txtId.setEnabled(true);
        txtRazon.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTipoComprobante.setEnabled(true);
        txtNumero.setEnabled(true);
        txtObservacion.setEnabled(true);
        txtFecha.setEnabled(true);

        txtIdPago.setText("");
        txtFormaDePago.setText("");
        txtIdBanco.setText("");
        txtBanco.setText("");
        txtSucursal.setText("");
        txtNumeroCH.setText("");
        txtVencimiento.setText("");
        txtImporte.setText("");
        txtTotal.setText("");
        

        txtIdPago.setEnabled(true);
        txtFormaDePago.setEnabled(true);
        txtIdBanco.setEnabled(true);
        txtBanco.setEnabled(true);
        txtSucursal.setEnabled(true);
        txtNumeroCH.setEnabled(true);
        txtVencimiento.setEnabled(true);
        txtImporte.setEnabled(true);

        btnGuardar.setEnabled(true);

    }

    public void inHabilitar() {

        txtId.setEnabled(false);
        txtRazon.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTipoComprobante.setEnabled(false);
        txtNumero.setEnabled(false);
        txtObservacion.setEnabled(false);
        txtFecha.setEnabled(false);

        txtIdPago.setEnabled(false);
        txtFormaDePago.setEnabled(false);
        txtIdBanco.setEnabled(false);
        txtBanco.setEnabled(false);
        txtSucursal.setEnabled(false);
        txtNumeroCH.setEnabled(false);
        txtVencimiento.setEnabled(false);
        txtImporte.setEnabled(false);

        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);

    }

    public void inHabilitarFORMAS() {

        txtIdBanco.setEnabled(false);
        txtBanco.setEnabled(false);
        txtSucursal.setEnabled(false);
        txtNumeroCH.setEnabled(false);
        txtVencimiento.setEnabled(false);
        txtImporte.setEnabled(false);

    }

    public void agregarData() {

        Object[] datos = new Object[]{txtIdPago.getText(), txtFormaDePago.getText(), txtIdBanco.getText(), txtBanco.getText(), txtSucursal.getText(), txtNumeroCH.getText(), txtVencimiento.getText(), txtImporte.getText()};

        DefaultTableModel model = (DefaultTableModel) tablePagos.getModel();
        model.addRow(datos);
        tablePagos.setModel(model);

        txtIdPago.setText("");
        txtFormaDePago.setText("");
        txtIdBanco.setText("");
        txtBanco.setText("");
        txtSucursal.setText("");
        txtVencimiento.setText("");
        txtNumeroCH.setText("");
        txtImporte.setText("");
        suma();
        inHabilitarFORMAS();
    }

    public void suma() {

        double valor = 0;

        int filas = tablePagos.getRowCount();

        for (int i = 0; i < filas; i++) {

            String tol = String.valueOf(tablePagos.getValueAt(i, 7));

            double total = Double.parseDouble(tol);

            valor = valor + total;

        }

        txtTotal.setText(String.valueOf(valor));

    }

    public void guarda() {

        if (txtId.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "FALTA RAZON SOCIAL", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
            txtId.requestFocus();

            return;

        }
        if (txtTipoComprobante.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "FALTA TIPO DE COMPROBANTE", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
            txtTipoComprobante.requestFocus();

            return;

        }
        if (txtTotal.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "FALTA IMPORTE", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
            txtTotal.requestFocus();

            return;

        }
        
        GenericRepository<dCobros, Long> func = new GenericRepository<dCobros, Long>(factory) {
        };
        GenericRepository<dOperadores, Long> funcOP = new GenericRepository<dOperadores, Long>(factory) {
        };

        
        obtenerFecha();

        dOperadores dtsOpera = funcOP.getById(Long.valueOf(txtId.getText()));

        dts.setOperadores(dtsOpera);
        if(txtNumero.getText().length()==0){
        dts.setComprobante("0");}
        else{
        dts.setComprobante(txtNumero.getText());}
        dts.setFecha(dato);
        dts.setImporte(Double.valueOf(txtTotal.getText()));
        dts.setDescripcion(txtFormaDePago.getText()+" "+txtObservacion.getText());
        dts.setTipoComprobante(txtTipoComprobante.getText());

        func.save(dts);
        cuentasCorriente();
        guardarDETALLE();
        inHabilitar();

    }

    public void guardarDETALLE() {

        GenericRepository<dCheques, Long> funcCH = new GenericRepository<dCheques, Long>(factory) {
        };
        GenericRepository<dFormaDePago, Long> funcFO = new GenericRepository<dFormaDePago, Long>(factory) {
        };
        GenericRepository<dBanco, Long> funcBCO = new GenericRepository<dBanco, Long>(factory) {
        };
        GenericRepository<ddetallecobros, Long> funcDTA = new GenericRepository<ddetallecobros, Long>(factory) {
        };
        
        dBanco banco = new dBanco();
         ddetallecobros detalle = new ddetallecobros();

        int fila = tablePagos.getRowCount();
        for (int x = 0; x < fila; x++) {

            String codPago = String.valueOf(tablePagos.getValueAt(x, 0));
            String codBco = String.valueOf(tablePagos.getValueAt(x, 2));
            String bco = String.valueOf(tablePagos.getValueAt(x, 3));
            String suc = String.valueOf(tablePagos.getValueAt(x, 4));
            String nCH = String.valueOf(tablePagos.getValueAt(x, 5));
            String fchVTO = String.valueOf(tablePagos.getValueAt(x, 6));
            String Importe = String.valueOf(tablePagos.getValueAt(x, 7));
            String formaPago = String.valueOf(tablePagos.getValueAt(x, 1));

           dFormaDePago  formPago = funcFO.getById(Long.valueOf(codPago));

            detalle.setCobros(dts);
            detalle.setFormaPago(formPago);
            

            if (formaPago.equals("CHEQUE PROPIO") || formaPago.equals("CHEQUE")) {

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

                detalle.setCategoria(formaPago);
                detalle.setIdBanco(banco);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setNumero(Integer.parseInt(nCH));
                detalle.setSucursal(Integer.parseInt(suc));
                detalle.setVencimiento(datoVTO);
                detalle.setImporte(Double.valueOf(Importe));
                funcDTA.save(detalle);
            }

            if (formaPago.equals("EFECTIVO")) {
                
                banco = funcBCO.getById(1l);
                
                detalle.setCategoria(formaPago);
                detalle.setVencimiento(dato);
                detalle.setImporte(Double.valueOf(Importe));
                detalle.setSucursal(0);
                detalle.setNumero(0);
                detalle.setBanco("----");
                detalle.setIdBanco(banco);
                
                funcDTA.save(detalle);
            }

            if (formaPago.equals("DEPOSITO")) {

                banco = funcBCO.getById(1l);
                
                detalle.setBanco(bco);
                detalle.setIdBanco(banco);
                detalle.setSucursal(0);
                detalle.setVencimiento(dato);
                detalle.setCategoria(formaPago);
                
                detalle.setNumero(0);
                detalle.setImporte(Double.valueOf(Importe));

                funcDTA.save(detalle);
            }
        }

    }

    public void cuentasCorriente() {

        GenericRepository<dCuentasCorriente, Long> funcCC = new GenericRepository<dCuentasCorriente, Long>(factory) {
        };
        dCuentasCorriente dtsCC = new dCuentasCorriente();
           GenericRepository<dOperadores, Long> funcOP = new GenericRepository<dOperadores, Long>(factory) {
        }; 
      
        dOperadores dtsOpera = funcOP.getById(Long.valueOf(txtId.getText()));  

        if (txtTipoComprobante.getText().equals("COBRO")) {
            dtsCC.setHaber(Double.valueOf(txtTotal.getText()));
            dtsCC.setDebe(0.0);
        }
        if (txtTipoComprobante.getText().equals("PAGO")) {
            dtsCC.setDebe(Double.valueOf(txtTotal.getText()));
            dtsCC.setHaber(0.0);
        }
        dtsCC.setFecha(dato);
        dtsCC.setObservacion(txtObservacion.getText());
        dtsCC.setOperadores(dtsOpera);
        dtsCC.setCobros(dts);
        dtsCC.setNumero(txtNumero.getText());
         dtsCC.setNumeroID(Integer.parseInt(dts.getIdcobros().toString()));
        funcCC.save(dtsCC);

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

    public void formasDePago() {

        if (txtFormaDePago.getText().equals("EFECTIVO")) {

            txtImporte.setEnabled(true);

        }
        if (txtFormaDePago.getText().equals("CHEQUE PROPIO") || txtFormaDePago.getText().equals("CHEQUE")) {

            txtIdBanco.setEnabled(true);
            txtBanco.setEnabled(true);
            txtSucursal.setEnabled(true);
            txtNumeroCH.setEnabled(true);
            txtVencimiento.setEnabled(true);
            txtImporte.setEnabled(true);

        }
        if (txtFormaDePago.getText().equals("DEPOSITO")) {

            txtIdBanco.setEnabled(true);
            txtBanco.setEnabled(true);
            txtImporte.setEnabled(true);

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
    
    public void saldo(){
    
        cuentasManager func=new cuentasManager(factory);
        
        double saldo= func.getSuma(Integer.parseInt(txtId.getText()));
        
         DecimalFormat format = new DecimalFormat("0.00");
         String saldoFinal=format.format(saldo);
//         System.out.println(saldo);
         lSaldo.setText("$ "+saldoFinal);
      //  txtSaldo.setText(String.valueOf(saldo));
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtRazon = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtObservacion = new javax.swing.JTextField();
        txtTipoComprobante = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        cboTipoComprobante = new javax.swing.JComboBox();
        txtFecha = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lSaldo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtIdPago = new javax.swing.JTextField();
        txtFormaDePago = new javax.swing.JTextField();
        txtIdBanco = new javax.swing.JTextField();
        txtBanco = new javax.swing.JTextField();
        txtSucursal = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        txtNumeroCH = new javax.swing.JTextField();
        txtVencimiento = new javax.swing.JFormattedTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        setTitle("COBROS Y PAGOS");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("FORMULARIO DE COBROS Y PAGOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel3.setText("Direccion");

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

        txtRazon.setEditable(false);
        txtRazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonActionPerformed(evt);
            }
        });

        txtDireccion.setEditable(false);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        txtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionActionPerformed(evt);
            }
        });

        txtTipoComprobante.setEditable(false);
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

        cboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COBRO", "PAGO" }));
        cboTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoComprobanteActionPerformed(evt);
            }
        });

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("ESTADO CUENTA");

        lSaldo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lSaldo.setText("$ 0.00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(lSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRazon))
                            .addComponent(txtObservacion)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Id");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Forma de pago");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Banco");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Suc.");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Importe");

        txtIdPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPagoActionPerformed(evt);
            }
        });
        txtIdPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdPagoKeyPressed(evt);
            }
        });

        txtFormaDePago.setEditable(false);
        txtFormaDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormaDePagoActionPerformed(evt);
            }
        });

        txtIdBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBancoActionPerformed(evt);
            }
        });
        txtIdBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdBancoKeyPressed(evt);
            }
        });

        txtBanco.setEditable(false);
        txtBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBancoActionPerformed(evt);
            }
        });

        txtSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSucursalActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtNumeroCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCHActionPerformed(evt);
            }
        });

        try {
            txtVencimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVencimientoActionPerformed(evt);
            }
        });

        txtImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteActionPerformed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Id");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Numero");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fecha.vto");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFormaDePago))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBanco)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumeroCH, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(txtImporte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(txtNumeroCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablePagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TIPO", "ID", "BANCO", "SUCURSAL", "NUMERO", "FECHA.VTO", "IMPORTE"
            }
        ));
        jScrollPane1.setViewportView(tablePagos);
        if (tablePagos.getColumnModel().getColumnCount() > 0) {
            tablePagos.getColumnModel().getColumn(0).setMaxWidth(50);
            tablePagos.getColumnModel().getColumn(1).setMaxWidth(300);
            tablePagos.getColumnModel().getColumn(2).setMaxWidth(50);
            tablePagos.getColumnModel().getColumn(3).setMaxWidth(300);
            tablePagos.getColumnModel().getColumn(4).setMaxWidth(50);
            tablePagos.getColumnModel().getColumn(5).setMaxWidth(300);
            tablePagos.getColumnModel().getColumn(6).setMaxWidth(200);
            tablePagos.getColumnModel().getColumn(7).setMaxWidth(200);
        }

        txtTotal.setEditable(false);

        jLabel17.setText("TOTAL");

        btnGuardar.setText("CARGAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton3.setText("IMPRIMIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addGap(0, 87, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
     GenericRepository<dOperadores, Long> funcOP = new GenericRepository<dOperadores, Long>(factory) {
        };
        dOperadores op = funcOP.getById(Long.valueOf(txtId.getText()));

        saldo();
        
        if(op!=null){
        txtRazon.setText(op.razonSocial);
        txtDireccion.setText(op.direccion);

        txtId.transferFocus();}
        else{
        JOptionPane.showMessageDialog(null,"no exite operador");
        return;
        }
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_Operador form = new vTabla_Operador();
            form.ventana = "cobros";
            form.toFront();
            form.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtRazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonActionPerformed
        txtRazon.transferFocus();
    }//GEN-LAST:event_txtRazonActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        txtDireccion.transferFocus();
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
        txtObservacion.transferFocus();
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void txtTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoComprobanteActionPerformed
        txtTipoComprobante.transferFocus();
    }//GEN-LAST:event_txtTipoComprobanteActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        txtNumero.transferFocus();
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtIdPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPagoActionPerformed
           GenericRepository<dFormaDePago, Long> funcFO = new GenericRepository<dFormaDePago, Long>(factory) {
      };
        dFormaDePago fp = funcFO.getById(Long.valueOf(txtIdPago.getText()));

        if(fp!=null){
        txtFormaDePago.setText(fp.formaPago);
        formasDePago();
        txtIdPago.transferFocus();
        }else{
        JOptionPane.showMessageDialog(null,"no es valido");
        return;
        }
    }//GEN-LAST:event_txtIdPagoActionPerformed

    private void txtIdPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdPagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_formaPago form = new vTabla_formaPago();

            form.toFront();
            form.setVisible(true);
        }
    }//GEN-LAST:event_txtIdPagoKeyPressed

    private void txtFormaDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormaDePagoActionPerformed
        txtFormaDePago.transferFocus();
    }//GEN-LAST:event_txtFormaDePagoActionPerformed

    private void txtIdBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBancoActionPerformed
        GenericRepository<dBanco, Long> funcBCO = new GenericRepository<dBanco, Long>(factory) {
        };
        dBanco ba = funcBCO.getById(Long.valueOf(txtIdBanco.getText()));

        if(ba!=null){
        txtBanco.setText(ba.nombre);

        txtIdBanco.transferFocus();
        }else{
        JOptionPane.showMessageDialog(null,"no exite banco");
        return;
        }
    }//GEN-LAST:event_txtIdBancoActionPerformed

    private void txtBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBancoActionPerformed
        txtBanco.transferFocus();
    }//GEN-LAST:event_txtBancoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarData();
        inHabilitarFORMAS();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guarda();
        limpiaTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    GenericRepository<dCobros, Long> func = new GenericRepository<dCobros, Long>(factory) {
    };
        
       dCobros co = func.getById(11L);

//         ddetallecobros de=new ddetallecobros();
//        de=funcDTA.getById(7L);
        // funcDTA.delete(de);
        func.delete(co);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tablePagos.getModel(); //TableProducto es el nombre de mi tabla ;)
        dtm.removeRow(tablePagos.getSelectedRow());
        suma();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoComprobanteActionPerformed
        txtTipoComprobante.setText(cboTipoComprobante.getSelectedItem().toString());
        txtObservacion.setText(txtTipoComprobante.getText());
    }//GEN-LAST:event_cboTipoComprobanteActionPerformed

    private void txtIdBancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdBancoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_banco form = new vTabla_banco();

            form.toFront();
            form.setVisible(true);
        }
    }//GEN-LAST:event_txtIdBancoKeyPressed

    private void txtSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSucursalActionPerformed
        txtSucursal.transferFocus();
    }//GEN-LAST:event_txtSucursalActionPerformed

    private void txtNumeroCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCHActionPerformed
        txtNumeroCH.transferFocus();
    }//GEN-LAST:event_txtNumeroCHActionPerformed

    private void txtVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVencimientoActionPerformed
        txtVencimiento.transferFocus();
    }//GEN-LAST:event_txtVencimientoActionPerformed

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteActionPerformed

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
            java.util.logging.Logger.getLogger(vCobrosYPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vCobrosYPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vCobrosYPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vCobrosYPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vCobrosYPagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cboTipoComprobante;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lSaldo;
    private javax.swing.JTable tablePagos;
    public static javax.swing.JTextField txtBanco;
    protected static javax.swing.JTextField txtDireccion;
    private javax.swing.JFormattedTextField txtFecha;
    public static javax.swing.JTextField txtFormaDePago;
    protected static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtIdBanco;
    public static javax.swing.JTextField txtIdPago;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroCH;
    private javax.swing.JTextField txtObservacion;
    protected static javax.swing.JTextField txtRazon;
    private javax.swing.JTextField txtSucursal;
    protected static javax.swing.JTextField txtTipoComprobante;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JFormattedTextField txtVencimiento;
    // End of variables declaration//GEN-END:variables
}
