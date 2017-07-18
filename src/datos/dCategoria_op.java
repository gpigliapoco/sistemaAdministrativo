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
@Table(name = "categoriaOP")
public class dCategoria_op implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcategoriaOP")
    public Long idcategoria;
    @Column(name = "categoria")
    public String categoria;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoriaOP")
    public List<dOperadores> operadores;

    public dCategoria_op(String categoria, List<dOperadores> operadores) {
        this.categoria = categoria;
        this.operadores = operadores;
    }

 

    public dCategoria_op() {
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<dOperadores> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<dOperadores> operadores) {
        this.operadores = operadores;
    }

  
    
    
    
    
    
}
