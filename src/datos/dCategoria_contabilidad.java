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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leo
 */
@Entity
@Table(name = "categoriaCONTA")
public class dCategoria_contabilidad implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategoriaConta")
    public Long idCategoriaConta;
    @Column(name = "categoria")
    public String categoria;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoriaConta",cascade = CascadeType.ALL)
    public List<dCuentaPlan> cuentaPlan;

    public dCategoria_contabilidad() {
    }

    public dCategoria_contabilidad(String categoria, List<dCuentaPlan> cuentaPlan) {
        this.categoria = categoria;
        this.cuentaPlan = cuentaPlan;
    }

    public Long getIdCategoriaConta() {
        return idCategoriaConta;
    }

    public void setIdCategoriaConta(Long idCategoriaConta) {
        this.idCategoriaConta = idCategoriaConta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<dCuentaPlan> getCuentaPlan() {
        return cuentaPlan;
    }

    public void setCuentaPlan(List<dCuentaPlan> cuentaPlan) {
        this.cuentaPlan = cuentaPlan;
    }

    
    
}
