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

@Entity
@Table(name = "categoriaVALE")

public class dCategoriaVALE implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategoriaVALE")
    public Long idCategoriaVALE;
    @Column(name = "categoria")
    public String categoria;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoriaVALE",cascade = CascadeType.ALL)
    public List<dSueldo> sueldo;

    public dCategoriaVALE() {
    }

    public dCategoriaVALE(String categoria, List<dSueldo> sueldo) {
        this.categoria = categoria;
        this.sueldo = sueldo;
    }

   

    public Long getIdCategoriaVALE() {
        return idCategoriaVALE;
    }

    public void setIdCategoriaVALE(Long idCategoriaVALE) {
        this.idCategoriaVALE = idCategoriaVALE;
    }

   
    
    
  
   
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<dSueldo> getSueldo() {
        return sueldo;
    }

    public void setSueldo(List<dSueldo> sueldo) {
        this.sueldo = sueldo;
    }

   
    
}
