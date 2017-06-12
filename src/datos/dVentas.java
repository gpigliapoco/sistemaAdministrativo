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
@Table(name = "ventas")
public class dVentas implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idVentas;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Column(name = "importe")
    public Double importe;
    @Column(name = "comprobante")
    public int comprobante;
    @Column(name = "descripcion")
    public String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idOperadores")
    public dOperadores operadores;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idtipocomprobante")
    public dTipoComprobante tipoComprobante;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "ventas")
    public List<dDetalleVta> detalleVta;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "ventas")
    public dCuentasCorriente cuenta;
            
    public dVentas() {
    }

    public dVentas(Date fecha, Double importe, int comprobante, String descripcion, dOperadores operadores, dTipoComprobante tipoComprobante, List<dDetalleVta> detalleVta) {
        this.fecha = fecha;
        this.importe = importe;
        this.comprobante = comprobante;
        this.descripcion = descripcion;
        this.operadores = operadores;
        this.tipoComprobante = tipoComprobante;
        this.detalleVta = detalleVta;
    }

  

  

    public Long getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Long idVentas) {
        this.idVentas = idVentas;
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

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public dOperadores getOperadores() {
        return operadores;
    }

    public void setOperadores(dOperadores operadores) {
        this.operadores = operadores;
    }

    public dTipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(dTipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public List<dDetalleVta> getDetalleVta() {
        return detalleVta;
    }

    public void setDetalleVta(List<dDetalleVta> detalleVta) {
        this.detalleVta = detalleVta;
    }

    public dCuentasCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(dCuentasCorriente cuenta) {
        this.cuenta = cuenta;
    }

   
    
    
    
}
