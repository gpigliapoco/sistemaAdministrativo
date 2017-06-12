/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "cobros")
public class dCobros implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCobro")
     public Long idcobros;
     @Temporal(TemporalType.DATE)
     @Column(name = "fecha")
     public Date fecha;
     @Column(name = "importe")
     public double importe;
     @Column(name = "descripcion")
     public String descripcion;
     @Column(name = "comprobante")
     public String comprobante;
     @Column(name = "tipocomprobante")
     public String tipoComprobante;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "fk_idoperadores")
     public dOperadores operadores;
     @OneToMany(fetch = FetchType.LAZY,mappedBy = "cobros",cascade = CascadeType.ALL)
     public List<ddetallecobros> detalleCobro;
     @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "cobros")
    public dCuentasCorriente cuenta;
     
     
    public dCobros() {
    }

    public dCobros(Date fecha, double importe, String descripcion, String comprobante, String tipoComprobante, dOperadores operadores, List<ddetallecobros> detalleCobro) {
        this.fecha = fecha;
        this.importe = importe;
        this.descripcion = descripcion;
        this.comprobante = comprobante;
        this.tipoComprobante = tipoComprobante;
        this.operadores = operadores;
        this.detalleCobro = detalleCobro;
    }

    public Long getIdcobros() {
        return idcobros;
    }

    public void setIdcobros(Long idcobros) {
        this.idcobros = idcobros;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public dOperadores getOperadores() {
        return operadores;
    }

    public void setOperadores(dOperadores operadores) {
        this.operadores = operadores;
    }

    public List<ddetallecobros> getDetalleCobro() {
        return detalleCobro;
    }

    public void setDetalleCobro(List<ddetallecobros> detalleCobro) {
        this.detalleCobro = detalleCobro;
    }

    public dCuentasCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(dCuentasCorriente cuenta) {
        this.cuenta = cuenta;
    }
     
     
    
    
}
