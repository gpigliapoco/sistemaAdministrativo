/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.List;
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

@Entity
@Table(name = "operadores")
public class dOperadores implements Serializable{
    
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOperadores")
    public Long idoperadores;
    @Column(name = "razonSocial")
    public String razonSocial;
    @Column(name = "cuit")
    public int cuit;
    @Column(name = "direccion")
    public String direccion;
    @Column(name = "localidad")
    public String localidad;
    @Column(name= "provincia")
    public String provincia;
    @Column(name = "telefono")
    public String telefono;
    @Column(name = "email")
    public String email;
    @Column(name = "estado")
    public String estado;
    @Column(name = "observaciones")
    public String observaciones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idcategoriaOP")
    public dCategoria_op categoriaOP;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idtransporte")
    public dTransporte transporte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idvendedores")
    public dVendedor vendedores;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operadores")
    public List<dVentas> ventas;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operadores")
    public List<dCompras> compras;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operadores")
    public List<dCobros> cobros;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operadores")
    public List<dCuentasCorriente> cuentas;

    public dOperadores(String razonSocial, int cuit, String direccion, String localidad, String provincia, String telefono, String email, String estado, String observaciones, dCategoria_op categoriaOP, dTransporte transporte, dVendedor vendedores, List<dVentas> ventas, List<dCompras> compras, List<dCobros> cobros, List<dCuentasCorriente> cuentas) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.observaciones = observaciones;
        this.categoriaOP = categoriaOP;
        this.transporte = transporte;
        this.vendedores = vendedores;
        this.ventas = ventas;
        this.compras = compras;
        this.cobros = cobros;
        this.cuentas = cuentas;
    }

    

    public dOperadores() {
    }

    public Long getIdoperadores() {
        return idoperadores;
    }

    public void setIdoperadores(Long idoperadores) {
        this.idoperadores = idoperadores;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public dCategoria_op getCategoriaOP() {
        return categoriaOP;
    }

    public void setCategoriaOP(dCategoria_op categoriaOP) {
        this.categoriaOP = categoriaOP;
    }

    public dTransporte getTransporte() {
        return transporte;
    }

    public void setTransporte(dTransporte transporte) {
        this.transporte = transporte;
    }

    public dVendedor getVendedores() {
        return vendedores;
    }

    public void setVendedores(dVendedor vendedores) {
        this.vendedores = vendedores;
    }

    public List<dVentas> getVentas() {
        return ventas;
    }

    public void setVentas(List<dVentas> ventas) {
        this.ventas = ventas;
    }

    public List<dCompras> getCompras() {
        return compras;
    }

    public void setCompras(List<dCompras> compras) {
        this.compras = compras;
    }

    public List<dCobros> getCobros() {
        return cobros;
    }

    public void setCobros(List<dCobros> cobros) {
        this.cobros = cobros;
    }

    public List<dCuentasCorriente> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<dCuentasCorriente> cuentas) {
        this.cuentas = cuentas;
    }
    
    
    
}
