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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "cheques")
public class dCheques implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCheques")
    public Long idCheques;
    @Column(name = "sucursal")
    public int Sucursal;
    @Column(name = "numero")
    public int numero;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimiento")
    public Date vencimiento;
    @Column(name = "importe")
    public Double importe;
    @Column(name = "banco")
    public String banco;
    @Column(name = "categoria")
    public String categoria;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_iddetalleCobro")
    public ddetallecobros detalleCobros;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idBanco")
    public dBanco idBanco;

    public dCheques(int Sucursal, int numero, Date fecha, Date vencimiento, Double importe, String banco, String categoria, ddetallecobros detalleCobros, dBanco idBanco) {
        this.Sucursal = Sucursal;
        this.numero = numero;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.importe = importe;
        this.banco = banco;
        this.categoria = categoria;
        this.detalleCobros = detalleCobros;
        this.idBanco = idBanco;
    }

   
    
    

    public dCheques() {
    }

    public Long getIdCheques() {
        return idCheques;
    }

    public void setIdCheques(Long idCheques) {
        this.idCheques = idCheques;
    }

    public int getSucursal() {
        return Sucursal;
    }

    public void setSucursal(int Sucursal) {
        this.Sucursal = Sucursal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

   
    public ddetallecobros getDetalleCobros() {
        return detalleCobros;
    }

    public void setDetalleCobros(ddetallecobros detalleCobros) {
        this.detalleCobros = detalleCobros;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public dBanco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(dBanco idBanco) {
        this.idBanco = idBanco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

  
    
    
}
