/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
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

/**
 *
 * @author leo
 */
@Entity
@Table(name = "cuentaPlan")
public class dCuentaPlan implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCuenta")
    public Long idCuenta;
    @Column(name = "nombre")
    public String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idCategoria_conta")
    public dCategoria_contabilidad categoriaConta;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cuentaPlan",cascade = CascadeType.ALL)
    public List<dContabilidad> cuentaPlan;

    public dCuentaPlan() {
    }

    public dCuentaPlan(String nombre, dCategoria_contabilidad categoriaConta, List<dContabilidad> cuentaPlan) {
        this.nombre = nombre;
        this.categoriaConta = categoriaConta;
        this.cuentaPlan = cuentaPlan;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public dCategoria_contabilidad getCategoriaConta() {
        return categoriaConta;
    }

    public void setCategoriaConta(dCategoria_contabilidad categoriaConta) {
        this.categoriaConta = categoriaConta;
    }

    public List<dContabilidad> getCuentaPlan() {
        return cuentaPlan;
    }

    public void setCuentaPlan(List<dContabilidad> cuentaPlan) {
        this.cuentaPlan = cuentaPlan;
    }


    
    
}
