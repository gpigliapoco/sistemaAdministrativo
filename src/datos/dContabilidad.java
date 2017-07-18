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
 * @author leo
 */
@Entity
@Table(name = "contabilidad")
public class dContabilidad implements Serializable{
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idContabilidad")
    public Long idContabilidad;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")  
    public Date fecha;
    @Column(name = "importe")
    public Double importe;    
    @Column(name = "observacion")
    public String observacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idCuenta")
    public dCuentaPlan cuentaPlan;

    public dContabilidad() {
    }

    public dContabilidad(Date fecha, Double importe, String observacion, dCuentaPlan cuentaPlan) {
        this.fecha = fecha;
        this.importe = importe;
        this.observacion = observacion;
        this.cuentaPlan = cuentaPlan;
    }

    public Long getIdContabilidad() {
        return idContabilidad;
    }

    public void setIdContabilidad(Long idContabilidad) {
        this.idContabilidad = idContabilidad;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public dCuentaPlan getCuentaPlan() {
        return cuentaPlan;
    }

    public void setCuentaPlan(dCuentaPlan cuentaPlan) {
        this.cuentaPlan = cuentaPlan;
    }

    
    
}
