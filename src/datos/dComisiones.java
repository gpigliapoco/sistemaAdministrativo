/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "comisiones")
public class dComisiones implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idComisiones")
    public Long idComisiones;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Column(name = "precio")
    public Double precio;
    @Column(name = "cantidad")
    public int cantidad;
    @Column(name = "comision")
    public Double comision;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idProductos")
    public dProductos idProducto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idVendedor")
    public dVendedor idVendedor;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idDetalleVta")
    public dDetalleVta idDetalleVta;

    public dComisiones() {
    }

    public dComisiones(Date fecha, Double precio, int cantidad, Double comision, dProductos idProducto, dVendedor idVendedor, dDetalleVta idDetalleVta) {
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.comision = comision;
        this.idProducto = idProducto;
        this.idVendedor = idVendedor;
        this.idDetalleVta = idDetalleVta;
    }

   

    public Long getIdComisiones() {
        return idComisiones;
    }

    public void setIdComisiones(Long idComisiones) {
        this.idComisiones = idComisiones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public dProductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(dProductos idProducto) {
        this.idProducto = idProducto;
    }

    public dVendedor getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(dVendedor idVendedor) {
        this.idVendedor = idVendedor;
    }

    public dDetalleVta getIdDetalleVta() {
        return idDetalleVta;
    }

    public void setIdDetalleVta(dDetalleVta idDetalleVta) {
        this.idDetalleVta = idDetalleVta;
    }

   
    
    
    
}
