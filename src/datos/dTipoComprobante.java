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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "tipo_comprobante")
public class dTipoComprobante implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTipo_comprobante")
    public Long idTipo_comprobante;
    @Column(name = "comprobante")
    public String comprobante;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tipoComprobante")
    public List<dVentas> ventas;

    public dTipoComprobante() {
    }

    public dTipoComprobante(String comprobante, List<dVentas> ventas) {
        this.comprobante = comprobante;
        this.ventas = ventas;
    }

    public Long getIdTipo_comprobante() {
        return idTipo_comprobante;
    }

    public void setIdTipo_comprobante(Long idTipo_comprobante) {
        this.idTipo_comprobante = idTipo_comprobante;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public List<dVentas> getVentas() {
        return ventas;
    }

    public void setVentas(List<dVentas> ventas) {
        this.ventas = ventas;
    }
    
    
    
}
