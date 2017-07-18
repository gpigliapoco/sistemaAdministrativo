/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "vendedores")
public class dVendedor implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvendedores")
    public Long idVendedor;
    @Column(name = "nombre")
    public String nombre;
    @Column(name = "direccion")
    public String direccion;
    @Column(name = "localidad")
    public String localidad;
    @Column(name = "provincia")
    public String provincia;
    @Column(name = "email")
    public String email;
    @Column(name = "telefono")
    public String  telefono;
    @Column(name = "foto")
    public Blob foto;
    @Column(name = "observacion")
    public String observacion;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vendedores",cascade = CascadeType.ALL)
    public List<dOperadores> operadores;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idVendedor",cascade = CascadeType.ALL)
    public List<dComisiones> comisiones;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idVendedor",cascade = CascadeType.ALL)
    public List<dPorcentajeComi> porcentajeComi;

    public dVendedor(String nombre, String direccion, String localidad, String provincia, String email, String telefono, Blob foto, String observacion, List<dOperadores> operadores) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.email = email;
        this.telefono = telefono;
        this.foto = foto;
        this.observacion = observacion;
        this.operadores = operadores;
    }

   

    public dVendedor() {
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public List<dOperadores> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<dOperadores> operadores) {
        this.operadores = operadores;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<dComisiones> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<dComisiones> comisiones) {
        this.comisiones = comisiones;
    }

    public List<dPorcentajeComi> getPorcentajeComi() {
        return porcentajeComi;
    }

    public void setPorcentajeComi(List<dPorcentajeComi> porcentajeComi) {
        this.porcentajeComi = porcentajeComi;
    }
    
    
    
    
    
    
}
