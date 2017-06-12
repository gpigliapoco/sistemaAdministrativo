/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Controllers.categoriaEMPLmanager;
import Controllers.empleadosManager;
import conexion.GenericRepository;
import conexion.SessionFactoryManager;
import conexion.SessionManager;
import datos.dCategoriaEMPL;
import datos.dEmpleados;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static vistas.vPrincipal.factory;
/**
 *
 * @author leo
 */
public class vEmpleados extends javax.swing.JInternalFrame {
    
//    SessionFactoryManager manager = new SessionFactoryManager();
//        SessionFactory factory = manager.getSessionFactory();
//    

   
    

dEmpleados dts=new dEmpleados();
dEmpleados dts1=new dEmpleados();


java.util.Date datoNaci = null;
java.util.Date datoIngreso = null;

    public vEmpleados() {
        initComponents();
        inHabilitar();
        mostrar();
        cbSeccion();
    }

    public void habilitar(){
    
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCiudad.setText("");
        txtNacionalidad.setText("");
        txtDni.setText("");
        txtIngreso.setText("");
        txtNacimiento.setText("");
        txtObservacion.setText("");
        txtTelefono.setText("");
        txtCategoria.setText("");
        txtFoto.setText("");
        
        txtNombre.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCiudad.setEnabled(true);
        txtDni.setEnabled(true);
        txtNacionalidad.setEnabled(true);
        txtIngreso.setEnabled(true);
        txtNacimiento.setEnabled(true);
        txtObservacion.setEnabled(true);
        txtTelefono.setEnabled(true);
      //  txtCategoria.setEnabled(true);
    
    }
    
    
    public void inHabilitar(){
    
        txtNombre.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCiudad.setEnabled(false);
        txtDni.setEnabled(false);
        txtNacionalidad.setEnabled(false);
        txtIngreso.setEnabled(false);
        txtNacimiento.setEnabled(false);
        txtObservacion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtCategoria.setEnabled(false);
    
    }
    
    public void guardar() throws FileNotFoundException, SQLException{
        
        if (txtNombre.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA NOMBRE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            
            return ;
            
        }
        if (txtDireccion.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA DIRECCION","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtDireccion.requestFocus();
            
            return ;
            
        }
        if (txtFoto.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"FALTA FOTO","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
            txtFoto.requestFocus();
            
            return ;
            
        }
   
        GenericRepository<dEmpleados,Long> func=new GenericRepository<dEmpleados, Long>(factory) {
        };
        categoriaEMPLmanager funcCATE=new categoriaEMPLmanager(factory);
        
        
        String photoFilePath=txtFoto.getText();
     Session s = SessionManager.getSession();    
    
        File file = new File(photoFilePath);
        FileInputStream inputStream = new FileInputStream(file);
        Blob blob = Hibernate.getLobCreator(s)
                            .createBlob(inputStream, file.length());
        dts.setFoto(blob);
        
        
        
        int seleccion = cboSeccion.getSelectedIndex();
         String seccion=(String) cboSeccion.getItemAt(seleccion);
         
         dCategoriaEMPL dtsCATE=funcCATE.getbyId(seccion);
         obtenerFechaIngreso();
         obtenerFechaNaciomiento();
    
    dts.setNombre(txtNombre.getText());
    dts.setDireccion(txtDireccion.getText());
    dts.setDni(Integer.parseInt(txtDni.getText()));
    dts.setNacimiento(datoNaci);
    dts.setIngreso(datoIngreso);
    dts.setObservacion(txtObservacion.getText());
    dts.setLocalidad(txtCiudad.getText());
    dts.setNacionalidad(txtNacionalidad.getText());
    dts.setTelefono(txtTelefono.getText());
    dts.setCategoria(dtsCATE);
    func.save(dts);
    JOptionPane.showMessageDialog(rootPane, "EMPLEADO GUARDADO");
    inHabilitar();
    mostrar();
    
    }
    
    public void mostrar(){
    
     DefaultTableModel model;
     
      empleadosManager funcEM=new empleadosManager(factory);

        List<dEmpleados> lista = funcEM.listadoEMP();
        String[] titulos = {"ID", "NOMBRE", "CIUDAD","SECCION"};
        model = new DefaultTableModel(null, titulos);
        
        Object data[] = new Object[4];
        for (dEmpleados lista1 : lista) {
            data[0] = lista1.getIdEmpleados();
            data[1] = lista1.getNombre();
            data[2] = lista1.getNacionalidad();
            data[3] = lista1.getCategoria().getSeccion();
            model.addRow(data);
        }
        tableEmpleados.setModel(model);
        tableEmpleados.getColumnModel().getColumn(0).setMaxWidth(50);
        tableEmpleados.getColumnModel().getColumn(1).setMaxWidth(400);
        tableEmpleados.getColumnModel().getColumn(2).setMaxWidth(200);
        tableEmpleados.getColumnModel().getColumn(3).setMaxWidth(300);
    
    
    }
    
      public void editar() throws FileNotFoundException, SQLException {

        GenericRepository<dEmpleados, Long> func = new GenericRepository<dEmpleados, Long>(factory) {
        };

        categoriaEMPLmanager funcCATE = new categoriaEMPLmanager(factory);
        
        if(txtFoto.getText().length()!=0){
        String photoFilePath = txtFoto.getText();
        Session s = SessionManager.getSession();

        File file = new File(photoFilePath);
        FileInputStream inputStream = new FileInputStream(file);
        Blob blob = Hibernate.getLobCreator(s).createBlob(inputStream, file.length());
        dts1.setFoto(blob);}
        

        int seleccion = cboSeccion.getSelectedIndex();
        String seccion = (String) cboSeccion.getItemAt(seleccion);

        dCategoriaEMPL dtsCATE = funcCATE.getbyId(seccion);
        obtenerFechaIngreso();
        obtenerFechaNaciomiento();

        dts1.setNombre(txtNombre.getText());
        dts1.setDireccion(txtDireccion.getText());
        dts1.setDni(Integer.parseInt(txtDni.getText()));
        dts1.setNacimiento(datoNaci);
        dts1.setIngreso(datoIngreso);
        dts1.setObservacion(txtObservacion.getText());
        dts1.setLocalidad(txtCiudad.getText());
        dts1.setNacionalidad(txtNacionalidad.getText());
        dts1.setTelefono(txtTelefono.getText());
        dts1.setCategoria(dtsCATE);
        
        func.update(dts1);
        JOptionPane.showMessageDialog(rootPane, "EMPLEADO EDITADO");
        inHabilitar();
        mostrar();

    }
    
    public void cbSeccion(){
    
        GenericRepository<dCategoriaEMPL,Long> funcEMPL=new GenericRepository<dCategoriaEMPL, Long>(factory) {
        };
    
        DefaultComboBoxModel md =new DefaultComboBoxModel();
        List<dCategoriaEMPL> lista = funcEMPL.getAll();
         
        for (dCategoriaEMPL lista1 : lista) {
            
            md.addElement(lista1.getSeccion());
        }
        
      
        
        cboSeccion.setModel(md);
    
    }
    
    public void obtenerFechaNaciomiento() {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");  ////// metodo para convertir string a date y colocarlo en jdatechooser   http://www.forosdelweb.com/f45/mysql-java-netbeans-java-sql-date-860869/
        String fecha = txtNacimiento.getText();

        try {

            datoNaci = formatoDelTexto.parse(fecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

    }
    
     public void obtenerFechaIngreso() {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");  ////// metodo para convertir string a date y colocarlo en jdatechooser   http://www.forosdelweb.com/f45/mysql-java-netbeans-java-sql-date-860869/
        String fecha = txtIngreso.getText();

        try {

            datoIngreso = formatoDelTexto.parse(fecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lFoto = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFoto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        cboSeccion = new javax.swing.JComboBox();
        txtNacimiento = new javax.swing.JFormattedTextField();
        txtIngreso = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEmpleados = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);
        setTitle("empleados");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("FORMULARIO EMPLEADOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Apellido y nombre");

        jLabel2.setText("D.N.I");

        jLabel3.setText("F.nacimiento");

        jLabel4.setText("Nacionalidad");

        jLabel5.setText("Direccion");

        jLabel6.setText("Ciudad");

        jLabel7.setText("Telefono");

        jLabel8.setText("Ingreso");

        jLabel9.setText("Categoria");

        txtCategoria.setEditable(false);

        jLabel10.setText("Estado");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACTIVO\t", "INACTIVO" }));

        jLabel11.setText("Observacion");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel12.setText("Foto");

        txtFoto.setEditable(false);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        cboSeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeccionActionPerformed(evt);
            }
        });

        try {
            txtNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtIngreso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton1.setText("FOTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(432, 432, 432))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDireccion)
                            .addComponent(txtNombre)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDni))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButton1)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEditar)
                    .addComponent(btnNuevo))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableEmpleados);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
            try {
            editar();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cboSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeccionActionPerformed
      txtCategoria.setText(cboSeccion.getSelectedItem().toString());
    }//GEN-LAST:event_cboSeccionActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    try {
        guardar();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tableEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmpleadosMouseClicked
         if (evt.getClickCount() == 1) {
            int fila = tableEmpleados.getSelectedRow();

            String cod = tableEmpleados.getValueAt(fila, 0).toString();
            
             empleadosManager funcEM=new empleadosManager(factory);
            
            long cod1 = Long.valueOf(cod);
           
            dts1 = funcEM.getOperadorById(cod1);

     Image foto = null;
            
            
            try {
                Blob blob=dts1.getFoto();
                foto=javax.imageio.ImageIO.read(blob.getBinaryStream());
            } catch (SQLException ex) {
                Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(vEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            
            lFoto.setIcon(new ImageIcon(foto.getScaledInstance(200, 200, 1)));

    
            
          
            txtNombre.setText(dts1.getNombre());
            txtDireccion.setText(dts1.getDireccion());
            txtCiudad.setText(dts1.getLocalidad());
            txtNacionalidad.setText(dts1.getNacionalidad());
            txtDni.setText(String.valueOf(dts1.getDni()));
            txtTelefono.setText(dts1.getTelefono());
            txtCategoria.setText(dts1.getCategoria().getSeccion());
            txtObservacion.setText(dts1.getObservacion());
            
            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
      
             String strDate = sm.format(dts1.getIngreso());
             String strDate1 = sm1.format(dts1.getNacimiento());
            try {
                //Converting the String back to java.util.Date
                Date dt = sm.parse(strDate);
                Date dt1 = sm.parse(strDate1);
            } catch (ParseException ex) {
                Logger.getLogger(vPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtIngreso.setText(strDate);
            txtNacimiento.setText(strDate1);
            
           
         
           
            
            btnEditar.setEnabled(true);
            

            txtNombre.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCiudad.setEnabled(true);
        txtDni.setEnabled(true);
        txtNacionalidad.setEnabled(true);
        txtIngreso.setEnabled(true);
        txtNacimiento.setEnabled(true);
        txtObservacion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtCategoria.setEnabled(true);
            
        }
    }//GEN-LAST:event_tableEmpleadosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File archivo;

        JFileChooser flcAbrirArchivo = new JFileChooser();
        flcAbrirArchivo.setFileFilter(new FileNameExtensionFilter("Archivo de imagenes", "JPG", "PNG"));
        int respuesta = flcAbrirArchivo.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            archivo = flcAbrirArchivo.getSelectedFile();
            txtFoto.setText(archivo.getAbsolutePath());
            Image foto = getToolkit().getImage(txtFoto.getText());
            foto = foto.getScaledInstance(150, 150, 1);
            lFoto.setIcon(new ImageIcon(foto));
            lFoto.setText(null);
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
            java.util.logging.Logger.getLogger(vEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JComboBox cboSeccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lFoto;
    private javax.swing.JTable tableEmpleados;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JFormattedTextField txtIngreso;
    private javax.swing.JFormattedTextField txtNacimiento;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
