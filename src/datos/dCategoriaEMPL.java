/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "categoriaEMPL")
public class dCategoriaEMPL implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategoriaEMPL")
    public Long idCategoriaEMPL;
    @Column(name = "seccion")
    public String seccion;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria")
    public List<dEmpleados> empleados;

    public dCategoriaEMPL() {
    }

    public dCategoriaEMPL(String seccion, List<dEmpleados> empleados) {
        this.seccion = seccion;
        this.empleados = empleados;
    }

    public Long getIdCategoriaEMPL() {
        return idCategoriaEMPL;
    }

    public void setIdCategoriaEMPL(Long idCategoriaEMPL) {
        this.idCategoriaEMPL = idCategoriaEMPL;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public List<dEmpleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<dEmpleados> empleados) {
        this.empleados = empleados;
    }
    
    
    
}
