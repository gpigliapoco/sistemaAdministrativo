/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "detalleVta")
public class dDetalleVta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDetalleVta")
    public Long iddetalleVta;
    @Column(name = "precio")
     public double precio;
    @Column(name = "cantidad")
     public int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idproductos")
     public dProductos productos;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fk_idventas")
     public dVentas ventas;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idDetalleVta")
    public List<dComisiones> comisiones;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idDetalleVta")
    public List<dStock> stock;
    
    public dDetalleVta() {
    }

    public dDetalleVta(double precio, int cantidad, dProductos productos, dVentas ventas) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.productos = productos;
        this.ventas = ventas;
    }

    public Long getIddetalleVta() {
        return iddetalleVta;
    }

    public void setIddetalleVta(Long iddetalleVta) {
        this.iddetalleVta = iddetalleVta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public dProductos getProductos() {
        return productos;
    }

    public void setProductos(dProductos productos) {
        this.productos = productos;
    }

    public dVentas getVentas() {
        return ventas;
    }

    public void setVentas(dVentas ventas) {
        this.ventas = ventas;
    }

    public List<dComisiones> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<dComisiones> comisiones) {
        this.comisiones = comisiones;
    }

    public List<dStock> getStock() {
        return stock;
    }

    public void setStock(List<dStock> stock) {
        this.stock = stock;
    }
    
    
}
