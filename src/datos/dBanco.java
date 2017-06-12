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
@Table(name = "banco")
public class dBanco implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBancos")
    public Long idBanco;
    @Column(name = "nombre")
    public String nombre;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idBanco")
    public List<ddetallecobros> ddetallecobros;

    public dBanco() {
    }

    public dBanco(String nombre, List<ddetallecobros> ddetallecobros) {
        this.nombre = nombre;
        this.ddetallecobros = ddetallecobros;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ddetallecobros> getDdetallecobros() {
        return ddetallecobros;
    }

    public void setDdetallecobros(List<ddetallecobros> ddetallecobros) {
        this.ddetallecobros = ddetallecobros;
    }

   
    
    
}
