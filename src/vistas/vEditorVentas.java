/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.comisionManager;
import Controllers.cuentasManager;
import Controllers.porcentajeComiManager;
import Controllers.stockManager;
import Controllers.ventasManager;
import conexion.GenericRepository;
import conexion.SessionFactoryManager;
import datos.dComisiones;
import datos.dCuentasCorriente;
import datos.dDetalleVta;
import datos.dOperadores;
import datos.dProductos;
import datos.dStock;
import datos.dTipoComprobante;
import datos.dVendedor;
import datos.dVentas;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.SessionFactory;
import static vistas.vPrincipal.factory;
/**
 *
 * @author BeltariT
 */
public class vEditorVentas extends javax.swing.JInternalFrame {
//    
//    SessionFactoryManager manager = new SessionFactoryManager();
//        SessionFactory factory = manager.getSessionFactory();
//        
//     GenericRepository<dDetalleVta, Long> funcDETT = new GenericRepository<dDetalleVta, Long>(factory) {
//        };
//
//    GenericRepository<dVentas, Long> funcVTA = new GenericRepository<dVentas, Long>(factory) {
//    };
//         GenericRepository<dCuentasCorriente, Long> funcCC = new GenericRepository<dCuentasCorriente, Long>(factory) {
//    };
    
//    ventasManager func = new ventasManager(factory);
    public static long cod1;

    dVentas dts = new dVentas();
//    dDetalleVta dtsVent = new dDetalleVta();
//    dOperadores dtsOpera = new dOperadores();
//    dTipoComprobante dtsComprobante = new dTipoComprobante();
//    dComisiones dtsComi = new dComisiones();
//    dVendedor dtsVendedor = new dVendedor();
//    dStock stock = new dStock();
//    dCuentasCorriente cuenta =new dCuentasCorriente();
    java.util.Date dato = null;

    dVentas dtsVTA = new dVentas();

    public vEditorVentas() {
        initComponents();

    }
    
     public void habilitar(){
        
        txtId.setText("");
        txtRazon.setText("");
        txtObservacion.setText("");
        txtTipoComprobante.setText("");
        txtIdVta.setText("");
        txtNumero.setText("");
        txtTotal.setText("");
        txtVendedor.setText("");
        txtIdVendedor.setText("");
        
        limpiaTabla();
    
    }

    public void mostrar() {

        DefaultTableModel model;
        
        ventasManager func = new ventasManager(factory);

        dtsVTA = func.getbyDetalle(cod1);

            //txtId.setText(dtsVTA.getOperadores().getIdoperadores().toString());
        //txtRazon.setText(dtsVTA.getOperadores().getRazonSocial());
        txtIdComprobante.setText(dtsVTA.getTipoComprobante().getIdTipo_comprobante().toString());
        txtTipoComprobante.setText(dtsVTA.getTipoComprobante().getComprobante());
        txtObservacion.setText(dtsVTA.getDescripcion());
        txtIdVendedor.setText(dtsVTA.getOperadores().getVendedores().getIdVendedor().toString());
        txtVendedor.setText(dtsVTA.getOperadores().getVendedores().getNombre());

        String[] titulos = {"ID", "COD", "PRODUCTO", " CANTIDAD", "PRECIO", "TOTAL"};
        model = new DefaultTableModel(null, titulos);

        Object data[] = new Object[6];
        List<dDetalleVta> list = dtsVTA.getDetalleVta();
        for (dDetalleVta list1 : list) {
            data[0] = list1.getIddetalleVta();
            data[1] = list1.getProductos().getIdProductos().toString();
            data[2] = list1.getProductos().getProductos();
            data[3] = list1.getCantidad();
            data[4] = list1.getPrecio();
            data[5] = list1.getCantidad() * list1.getPrecio();

            model.addRow(data);

        }
        tableVenta.setModel(model);
        tableVenta.getColumnModel().getColumn(0).setMaxWidth(50);
        tableVenta.getColumnModel().getColumn(1).setMaxWidth(50);
        tableVenta.getColumnModel().getColumn(3).setMaxWidth(100);
        tableVenta.getColumnModel().getColumn(4).setMaxWidth(100);
        tableVenta.getColumnModel().getColumn(5).setMaxWidth(150);
        suma();

    }

    public void suma() {

        double valor = 0;

        int filas = tableVenta.getRowCount();

        for (int i = 0; i < filas; i++) {

            String tol = String.valueOf(tableVenta.getValueAt(i, 5));

            double total = Double.parseDouble(tol);

            valor = valor + total;

        }

        txtTotal.setText(String.valueOf(valor));

    }

    public void editar() {
        
        GenericRepository<dVentas, Long> funcVTA = new GenericRepository<dVentas, Long>(factory) {
    };
         GenericRepository<dCuentasCorriente, Long> funcCC = new GenericRepository<dCuentasCorriente, Long>(factory) {
    };
        
        GenericRepository<dTipoComprobante, Long> funcCO = new GenericRepository<dTipoComprobante, Long>(factory) {
        };

        cuentasManager funcCCID=new cuentasManager(factory);
        
        obtenerFecha();
       dTipoComprobante dtsComprobante = funcCO.getById(Long.valueOf(txtIdComprobante.getText()));
       dCuentasCorriente dtsCC=funcCCID.getByIdventas(cod1);

        dts.setOperadores(dtsVTA.getOperadores());

        dts.setDescripcion(txtObservacion.getText());
        dts.setFecha(dato);
        dts.setTipoComprobante(dtsComprobante);
        dts.setImporte(Double.parseDouble(txtTotal.getText()));
        dts.setComprobante(Integer.parseInt(txtNumero.getText()));

        dts.setIdVentas(cod1);
        funcVTA.update(dts);
        
        dCuentasCorriente cuenta=new dCuentasCorriente();
        
        cuenta.setDebe(Double.parseDouble(txtTotal.getText()));
        cuenta.setHaber(0.0);
        cuenta.setOperadores(dtsVTA.getOperadores());
        cuenta.setFecha(dato);
        cuenta.setObservacion(txtObservacion.getText());
        cuenta.setVentas(dts);
        cuenta.setIdCuentas(dtsCC.getIdCuentas());
        cuenta.setNumero(txtNumero.getText());
        cuenta.setNumeroID(Integer.parseInt(txtIdVta.getText()));
        funcCC.update(cuenta);
        
        guardarDetalleVTA();
        JOptionPane.showMessageDialog(rootPane, "COMPROBANTE EDITADO");
        habilitar();
    }

    public void guardarDetalleVTA() {

     
       

        GenericRepository<dProductos, Long> funcPRO = new GenericRepository<dProductos, Long>(factory) {
        };

        GenericRepository<dStock, Long> funcSTO = new GenericRepository<dStock, Long>(factory) {
        };
        GenericRepository<dComisiones, Long> funcCOMI = new GenericRepository<dComisiones, Long>(factory) {
        };

        GenericRepository<dVendedor, Long> funcVENDED = new GenericRepository<dVendedor, Long>(factory) {
        };
        
        GenericRepository<dDetalleVta, Long> funcDETT = new GenericRepository<dDetalleVta, Long>(factory) {
       };

        dVendedor dtsVendedor = funcVENDED.getById(Long.valueOf(txtIdVendedor.getText()));

        int fila = tableVenta.getRowCount();
        for (int x = 0; x < fila; x++) {

            String cod = String.valueOf(tableVenta.getValueAt(x, 1));
            String cant = String.valueOf(tableVenta.getValueAt(x, 3));
            String pre = String.valueOf(tableVenta.getValueAt(x, 4));
            String idDD = String.valueOf(tableVenta.getValueAt(x, 0));

            dProductos dtsProductos = new dProductos();
            dtsProductos = funcPRO.getById(Long.valueOf(cod));

            Long detalleID = Long.valueOf(idDD);
            System.out.println("id_Detalle " + idDD);

            porcentajeComiManager funcM = new porcentajeComiManager(factory);
            Long pr = funcM.getProductosCategoria(Long.valueOf(cod));
            int categoria = Integer.parseInt(pr.toString());
            System.out.println(pr.toString());
            System.out.println(categoria);
            Double comi = funcM.getComisionProducto(Integer.parseInt(txtIdVendedor.getText()), categoria);

            System.out.println(comi);

            Double operacion = Double.valueOf(cant) * Double.valueOf(pre);
            Double result = (operacion / 100) * comi;

            dVentas dts1 = new dVentas();

            stockManager funcST = new stockManager(factory);
            dStock sto = funcST.getbyIdStockVTA(detalleID);
            Long codStock = sto.getIdStock();

            comisionManager funcCO = new comisionManager(factory);
            dComisiones com = funcCO.getbyIdComision(detalleID);
            Long codComision = com.getIdComisiones();
            
            dDetalleVta dtsVent = new dDetalleVta();

            dtsVent.setCantidad(Integer.parseInt(cant));
            dtsVent.setPrecio(Double.parseDouble(pre));
            dtsVent.setProductos(dtsProductos);
            dtsVent.setVentas(dts);
            dtsVent.setIddetalleVta(detalleID);
            funcDETT.update(dtsVent);

            dStock stock=new dStock();
            stock.setFecha(dato);
            stock.setProducto(dtsProductos);
            stock.setCantidad(0);
            stock.setSalida(Integer.parseInt(cant));
            stock.setIdDetalleVta(dtsVent);
            stock.setIdStock(codStock);
            funcSTO.update(stock);

            dComisiones dtsComi=new dComisiones();
            if(categoria !=3){
            dtsComi.setFecha(dato);
            dtsComi.setCantidad(Integer.parseInt(cant));
            dtsComi.setPrecio(Double.parseDouble(pre));
            dtsComi.setIdProducto(dtsProductos);
            dtsComi.setIdDetalleVta(dtsVent);
            dtsComi.setIdVendedor(dtsVendedor);
            dtsComi.setComision(result);
            dtsComi.setIdComisiones(codComision);
            funcCOMI.update(dtsComi);}
           else{System.out.println("es materia prima");} 
             
             
             
        }
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

     public void limpiaTabla() { /// METODO LIMPIAR TABLA (NO FUNCIONA)
        try {
            DefaultTableModel temp = (DefaultTableModel) tableVenta.getModel();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        txtIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtRazon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdComprobante = new javax.swing.JTextField();
        txtTipoComprobante = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtIdVta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdVendedor = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setTitle("EDITOR DE VENTAS");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("EDITAR Y ELIMINAR COMPROBANTES DE VENTA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(0).setMaxWidth(50);
            tableVenta.getColumnModel().getColumn(1).setMaxWidth(50);
            tableVenta.getColumnModel().getColumn(3).setMaxWidth(100);
            tableVenta.getColumnModel().getColumn(4).setMaxWidth(100);
            tableVenta.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        jLabel14.setText("IVA");

        jLabel15.setText("TOTAL");

        btnGuardar.setText("EDITAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("ELIMINAR");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnMostrar.setText("TRAER FACTURA");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addContainerGap())))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(btnGuardar)
                    .addComponent(btnMostrar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btnNuevo))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Id Cliente");

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

        jLabel6.setText("Tipo Comprobante");

        txtIdComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdComprobanteActionPerformed(evt);
            }
        });
        txtIdComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdComprobanteKeyPressed(evt);
            }
        });

        txtTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoComprobanteActionPerformed(evt);
            }
        });

        jLabel8.setText("Observacion");

        txtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtIdVta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVtaActionPerformed(evt);
            }
        });

        jLabel3.setText("Id.Vta");

        jLabel5.setText("Vendedor");

        txtIdVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVendedorActionPerformed(evt);
            }
        });

        txtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorActionPerformed(evt);
            }
        });

        jLabel7.setText("Numero");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservacion))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdVta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero)
                .addGap(82, 82, 82))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdVta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        editar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tableVenta.getModel(); //TableProducto es el nombre de mi tabla ;)
        int fila = tableVenta.getSelectedRow();
        String codigo=tableVenta.getValueAt(fila, 0).toString();
        GenericRepository<dDetalleVta, Long> funcDETT = new GenericRepository<dDetalleVta, Long>(factory) {
        };
        
        System.out.println(codigo);
        
       dDetalleVta dtsEliminar=funcDETT.getById(Long.valueOf(codigo));
       funcDETT.delete(dtsEliminar);
        
        dtm.removeRow(tableVenta.getSelectedRow());

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
GenericRepository<dVentas, Long> funcVTA = new GenericRepository<dVentas, Long>(factory) {
   };
     //   editar();
        dVentas vta=funcVTA.getById(cod1);
        
        funcVTA.delete(vta);
        JOptionPane.showMessageDialog(rootPane, "COMPROBANTE ELIMINADO");
        limpiaTabla();
        habilitar();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        txtId.transferFocus();
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_facturasVta form = new vTabla_facturasVta();

        form.toFront();
        form.setVisible(true);

        }   // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtRazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonActionPerformed
        txtRazon.transferFocus();
    }//GEN-LAST:event_txtRazonActionPerformed

    private void txtIdComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdComprobanteActionPerformed
        txtIdComprobante.transferFocus();
    }//GEN-LAST:event_txtIdComprobanteActionPerformed

    private void txtIdComprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdComprobanteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            vTabla_Comprob form = new vTabla_Comprob();

            form.toFront();
            form.setVisible(true);
        }
    }//GEN-LAST:event_txtIdComprobanteKeyPressed

    private void txtTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoComprobanteActionPerformed
        txtTipoComprobante.transferFocus();
    }//GEN-LAST:event_txtTipoComprobanteActionPerformed

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
        txtObservacion.transferFocus();
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        txtFecha.transferFocus();
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtIdVtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVtaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVtaActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        mostrar();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void txtIdVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVendedorActionPerformed
        txtIdVendedor.transferFocus();
    }//GEN-LAST:event_txtIdVendedorActionPerformed

    private void txtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendedorActionPerformed
        txtVendedor.transferFocus();
    }//GEN-LAST:event_txtVendedorActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        txtNumero.transferFocus();
    }//GEN-LAST:event_txtNumeroActionPerformed

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
            java.util.logging.Logger.getLogger(vEditorVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vEditorVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vEditorVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vEditorVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vEditorVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableVenta;
    public static javax.swing.JTextField txtFecha;
    protected static javax.swing.JTextField txtId;
    protected static javax.swing.JTextField txtIdComprobante;
    protected static javax.swing.JTextField txtIdVendedor;
    public static javax.swing.JTextField txtIdVta;
    private javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtObservacion;
    protected static javax.swing.JTextField txtRazon;
    protected static javax.swing.JTextField txtTipoComprobante;
    private javax.swing.JTextField txtTotal;
    protected static javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
