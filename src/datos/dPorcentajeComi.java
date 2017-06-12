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
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "porcentajeComi")
public class dPorcentajeComi implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPorcentaje")
    public Long idPorcentaje;
    @Column(name = "comisionPorc")
    public Double comisionPorc;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idVendedores")
    public dVendedor idVendedor;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idCategoriaPR")
    public dCategoriaPR idCategoriaPR;

    public dPorcentajeComi() {
    }

    public dPorcentajeComi(Double comisionPorc, dVendedor idVendedor, dCategoriaPR idCategoriaPR) {
        this.comisionPorc = comisionPorc;
        this.idVendedor = idVendedor;
        this.idCategoriaPR = idCategoriaPR;
    }

    public Long getIdPorcentaje() {
        return idPorcentaje;
    }

    public void setIdPorcentaje(Long idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
    }

    public Double getComisionPorc() {
        return comisionPorc;
    }

    public void setComisionPorc(Double comisionPorc) {
        this.comisionPorc = comisionPorc;
    }

    public dVendedor getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(dVendedor idVendedor) {
        this.idVendedor = idVendedor;
    }

    public dCategoriaPR getIdCategoriaPR() {
        return idCategoriaPR;
    }

    public void setIdCategoriaPR(dCategoriaPR idCategoriaPR) {
        this.idCategoriaPR = idCategoriaPR;
    }
    
    
    
}
