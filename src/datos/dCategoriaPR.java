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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "categoriaPR")
public class dCategoriaPR implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategoriaPr")
    public Long idCategoriaPR;
    @Column(name = "categoria")
    public String categoria;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria",cascade = CascadeType.ALL)
    public List<dProductos> productos;

    public dCategoriaPR() {
    }

    public dCategoriaPR(String categoria, List<dProductos> productos) {
        this.categoria = categoria;
        this.productos = productos;
    }

    public Long getIdCategoriaPR() {
        return idCategoriaPR;
    }

    public void setIdCategoriaPR(Long idCategoriaPR) {
        this.idCategoriaPR = idCategoriaPR;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<dProductos> getProductos() {
        return productos;
    }

    public void setProductos(List<dProductos> productos) {
        this.productos = productos;
    }
    
    
    
}
