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
@Table(name = "detalleCompra")
public class dDetalleCpr implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddetalleCompra")
    public Long idDetalleCompra;
    @Column(name = "precio")
    public double precio;
    @Column(name = "cantidad")
    public int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idproductos")
    public dProductos productos;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fk_idcompras")
    public dCompras compras;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idDetalleCpr",cascade = CascadeType.ALL)
     public List<dStock> stock;

    public dDetalleCpr() {
    }

    public dDetalleCpr(double precio, int cantidad, dProductos productos, dCompras compras) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.productos = productos;
        this.compras = compras;
    }

    public Long getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Long idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
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

    public dCompras getCompras() {
        return compras;
    }

    public void setCompras(dCompras compras) {
        this.compras = compras;
    }

    public List<dStock> getStock() {
        return stock;
    }

    public void setStock(List<dStock> stock) {
        this.stock = stock;
    }

    
}
