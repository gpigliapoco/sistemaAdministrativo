/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "sueldo")
public class dSueldo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSueldo")
    public Long IdSueldo;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    public Date fecha;
    @Column(name = "importe")
    public Double importe;
    @Column(name = "observacion")
    public String observacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idEmpleados")
    public dEmpleados empleados;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idCategoriaVALE")
    public dCategoriaVALE categoriaVALE;

    public dSueldo() {
    }

    public dSueldo(Date fecha, Double importe, String observacion, dEmpleados empleados, dCategoriaVALE categoriaVALE) {
        this.fecha = fecha;
        this.importe = importe;
        this.observacion = observacion;
        this.empleados = empleados;
        this.categoriaVALE = categoriaVALE;
    }

   

    

    public Long getIdSueldo() {
        return IdSueldo;
    }

    public void setIdSueldo(Long IdSueldo) {
        this.IdSueldo = IdSueldo;
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

    public dEmpleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(dEmpleados empleados) {
        this.empleados = empleados;
    }

   

  
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public dCategoriaVALE getCategoriaVALE() {
        return categoriaVALE;
    }

    public void setCategoriaVALE(dCategoriaVALE categoriaVALE) {
        this.categoriaVALE = categoriaVALE;
    }

   
    
    
    
}
