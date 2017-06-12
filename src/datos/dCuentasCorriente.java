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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "cuentaCorriente")
public class dCuentasCorriente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCuentas")
    public Long idCuentas;
    @Column(name = "debe")
    public Double debe;
    @Column(name = "haber")
    public Double haber;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Column(name = "observacion" )
    public String observacion;
    @Column(name = "numero")
    public String numero;
    @Column(name = "numeroID")
    public int numeroID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idOperadores")
    public dOperadores operadores;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idVentas")
    public dVentas ventas;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idCompras")
    public dCompras compras;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idCobros")
    public dCobros cobros;

    public dCuentasCorriente(Double debe, Double haber, Date fecha, String observacion, String numero, int numeroID, dOperadores operadores, dVentas ventas, dCompras compras, dCobros cobros) {
        this.debe = debe;
        this.haber = haber;
        this.fecha = fecha;
        this.observacion = observacion;
        this.numero = numero;
        this.numeroID = numeroID;
        this.operadores = operadores;
        this.ventas = ventas;
        this.compras = compras;
        this.cobros = cobros;
    }

   

    public dCuentasCorriente() {
    }

    public Long getIdCuentas() {
        return idCuentas;
    }

    public void setIdCuentas(Long idCuentas) {
        this.idCuentas = idCuentas;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public dCobros getCobros() {
        return cobros;
    }

    public void setCobros(dCobros cobros) {
        this.cobros = cobros;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }
    
    
}
