/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
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
 * @author mike
 */
@Entity
@Table(name = "iva")
public class dIva implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idIva;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Column(name = "ivaVta")
    public Double ivaVta;
    @Column(name = "ivaCpr")
    public Double ivaCpr;
    @Column(name = "otros")
    public Double otros;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idOperadores")
    public dOperadores operadores;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idVentas")
    public dVentas ventas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idCompras")
    public dCompras compras;

    public dIva() {
    }

    public dIva(Date fecha, Double ivaVta, Double ivaCpr, Double otros, dOperadores operadores, dVentas ventas, dCompras compras) {
        this.fecha = fecha;
        this.ivaVta = ivaVta;
        this.ivaCpr = ivaCpr;
        this.otros = otros;
        this.operadores = operadores;
        this.ventas = ventas;
        this.compras = compras;
    }

    public Long getIdIva() {
        return idIva;
    }

    public void setIdIva(Long idIva) {
        this.idIva = idIva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getIvaVta() {
        return ivaVta;
    }

    public void setIvaVta(Double ivaVta) {
        this.ivaVta = ivaVta;
    }

    public Double getIvaCpr() {
        return ivaCpr;
    }

    public void setIvaCpr(Double ivaCpr) {
        this.ivaCpr = ivaCpr;
    }

    public Double getOtros() {
        return otros;
    }

    public void setOtros(Double otros) {
        this.otros = otros;
    }

    public dOperadores getOperadores() {
        return operadores;
    }

    public void setOperadores(dOperadores operadores) {
        this.operadores = operadores;
    }

    public dVentas getVentas() {
        return ventas;
    }

    public void setVentas(dVentas ventas) {
        this.ventas = ventas;
    }

    public dCompras getCompras() {
        return compras;
    }

    public void setCompras(dCompras compras) {
        this.compras = compras;
    }
    
    
            
}

