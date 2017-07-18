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
@Table(name = "formaDePago")
public class dFormaDePago implements Serializable{
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFormaDePago")
    public Long idFormaDePago;
    @Column(name = "formaPago")
    public String formaPago;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "formaPago")
    public List<ddetallecobros> detalleCobro;
    

    public dFormaDePago() {
    }

    public dFormaDePago(String formaPago, List<ddetallecobros> detalleCobro) {
        this.formaPago = formaPago;
        this.detalleCobro = detalleCobro;
    }

    public Long getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(Long idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<ddetallecobros> getDetalleCobro() {
        return detalleCobro;
    }

    public void setDetalleCobro(List<ddetallecobros> detalleCobro) {
        this.detalleCobro = detalleCobro;
    }

   
    
    
}
