/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
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
@Table(name = "productos")
public class dProductos implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproductos")
    public Long idProductos;
    @Column(name = "producto")
    public String productos;
    @Column(name = "marca")
    public String marca;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idcategoriaPR")
    public dCategoriaPR categoria;

    public dProductos(String productos, String marca, dCategoriaPR categoria) {
        this.productos = productos;
        this.marca = marca;
        this.categoria = categoria;
    }

    public dProductos() {
    }
    
    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public dCategoriaPR getCategoria() {
        return categoria;
    }

    public void setCategoria(dCategoriaPR categoria) {
        this.categoria = categoria;
    }
    
    
}
