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
@Table(name = "stock")
public class dStock implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idStock")
    public Long idStock;
    @Column(name = "cantidad")
    public int cantidad;
    @Column(name = "salida")
    public int salida;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idproductos")
    public dProductos producto;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idDetalleVTA")
    public dDetalleVta idDetalleVta;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idDetalleCPR")
    public dDetalleCpr idDetalleCpr;

    public dStock() {
    }

    public dStock(int cantidad, int salida, Date fecha, dProductos producto, dDetalleVta idDetalleVta, dDetalleCpr idDetalleCpr) {
        this.cantidad = cantidad;
        this.salida = salida;
        this.fecha = fecha;
        this.producto = producto;
        this.idDetalleVta = idDetalleVta;
        this.idDetalleCpr = idDetalleCpr;
    }

   

   

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public dProductos getProducto() {
        return producto;
    }

    public void setProducto(dProductos producto) {
        this.producto = producto;
    }

    public dDetalleVta getIdDetalleVta() {
        return idDetalleVta;
    }

    public void setIdDetalleVta(dDetalleVta idDetalleVta) {
        this.idDetalleVta = idDetalleVta;
    }

    public dDetalleCpr getIdDetalleCpr() {
        return idDetalleCpr;
    }

    public void setIdDetalleCpr(dDetalleCpr idDetalleCpr) {
        this.idDetalleCpr = idDetalleCpr;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

   
    
    
    
    
    
}
