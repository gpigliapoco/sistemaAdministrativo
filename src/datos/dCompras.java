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
import org.hibernate.annotations.Type;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "compras")
public class dCompras implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     public Long idcompras;
     @Temporal(TemporalType.DATE)
     @Column(name = "fecha")
     public Date fecha;
     @Column(name = "importe")
     public Double importe;
     @Column(name = "comprobante")
     public Integer comprobante;
     @Column(name = "descripcion")
     public String descripcion;
     @Column(name = "tipoComprobante")
     public String tipoComprobante;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "fk_idoperadores")
     public dOperadores operadores;
     @OneToMany(fetch = FetchType.LAZY,mappedBy = "compras",cascade = CascadeType.ALL)
     public List<dDetalleCpr> detalleCpr;
     @OneToOne(fetch = FetchType.LAZY,mappedBy = "compras",cascade = CascadeType.ALL)
    public dCuentasCorriente cuenta;

    public dCompras() {
    }

    public dCompras(Date fecha, Double importe, Integer comprobante, String descripcion, String tipoComprobante, dOperadores operadores, List<dDetalleCpr> detalleCpr) {
        this.fecha = fecha;
        this.importe = importe;
        this.comprobante = comprobante;
        this.descripcion = descripcion;
        this.tipoComprobante = tipoComprobante;
        this.operadores = operadores;
        this.detalleCpr = detalleCpr;
    }

    public Long getIdcompras() {
        return idcompras;
    }

    public void setIdcompras(Long idcompras) {
        this.idcompras = idcompras;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getComprobante() {
        return comprobante;
    }

    public void setComprobante(Integer comprobante) {
        this.comprobante = comprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<dDetalleCpr> getDetalleCpr() {
        return detalleCpr;
    }

    public void setDetalleCpr(List<dDetalleCpr> detalleCpr) {
        this.detalleCpr = detalleCpr;
    }

    public dCuentasCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(dCuentasCorriente cuenta) {
        this.cuenta = cuenta;
    }

   
      
     
    
}
