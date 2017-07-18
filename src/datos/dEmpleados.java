/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "empleados")
public class dEmpleados implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEmpleados")
    public Long idEmpleados;
    @Column(name = "nombre")
    public String nombre;
    @Column(name = "dni")
    public int dni;
    @Temporal(TemporalType.DATE)
    @Column(name = "nacimiento")
    public Date nacimiento;
    @Column(name = "nacionalidad")
    public String nacionalidad;
    @Column(name = "direccion")
    public String direccion;
    @Column(name = "localidad")
    public String localidad;
    @Column(name = "telefono")
    public String telefono;
    @Column(name = "observacion")
    public String observacion;
    @Temporal(TemporalType.DATE)
    @Column(name = "ingreso")
    public Date ingreso;
    @Column(name = "foto")
    public Blob foto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idCategoria")
    public dCategoriaEMPL categoria;

    
    public dEmpleados() {
    }

    public dEmpleados(String nombre, int dni, Date nacimiento, String nacionalidad, String direccion, String localidad, String telefono, String observacion, Date ingreso, Blob foto, dCategoriaEMPL categoria) {
        this.nombre = nombre;
        this.dni = dni;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.observacion = observacion;
        this.ingreso = ingreso;
        this.foto = foto;
        this.categoria = categoria;
    }

    public Long getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(Long idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public dCategoriaEMPL getCategoria() {
        return categoria;
    }

    public void setCategoria(dCategoriaEMPL categoria) {
        this.categoria = categoria;
    }
    
    

   
    
}
