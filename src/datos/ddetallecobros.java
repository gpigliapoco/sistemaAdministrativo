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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "detalleCobro")
public class ddetallecobros implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddetalleCobro")
    public Long iddetalleCobro;
    @Column(name = "importe")
    public double importe;
    @Column(name = "sucursal")
    public int Sucursal;
    @Column(name = "numero")
    public int numero;
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimiento")
    public Date vencimiento;
    @Column(name = "banco")
    public String banco;
    @Column(name = "categoria")
    public String categoria;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fk_idcobros")
    public dCobros cobros;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idFormaPago")
    public dFormaDePago formaPago;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idBanco")
    public dBanco idBanco;

    public ddetallecobros() {
    }

    public ddetallecobros(double importe, int Sucursal, int numero, Date vencimiento, String banco, String categoria, dCobros cobros, dFormaDePago formaPago, dBanco idBanco) {
        this.importe = importe;
        this.Sucursal = Sucursal;
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.banco = banco;
        this.categoria = categoria;
        this.cobros = cobros;
        this.formaPago = formaPago;
        this.idBanco = idBanco;
    }

    public Long getIddetalleCobro() {
        return iddetalleCobro;
    }

    public void setIddetalleCobro(Long iddetalleCobro) {
        this.iddetalleCobro = iddetalleCobro;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public dCobros getCobros() {
        return cobros;
    }

    public void setCobros(dCobros cobros) {
        this.cobros = cobros;
    }

    public dFormaDePago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(dFormaDePago formaPago) {
        this.formaPago = formaPago;
    }

    public dBanco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(dBanco idBanco) {
        this.idBanco = idBanco;
    }

    

   
    
    
}
