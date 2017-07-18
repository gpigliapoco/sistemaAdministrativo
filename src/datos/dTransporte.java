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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BeltariT
 */
@Entity
@Table(name = "transporte")
public class dTransporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtransporte")
    public Long idTrasnporte;
    @Column(name = "nombre")
    public String nombre;
    @Column(name = "direccion")
    public String direccion;
    @Column(name = "ciudad")
    public String ciudad;
    @Column(name = "telefono")
    public String telefono;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "transporte")
    public List<dOperadores> operadores;

    public dTransporte(String nombre, String direccion, String ciudad, String telefono, List<dOperadores> operadores) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.operadores = operadores;
    }

    public dTransporte() {
    }

    public Long getIdTrasnporte() {
        return idTrasnporte;
    }

    public void setIdTrasnporte(Long idTrasnporte) {
        this.idTrasnporte = idTrasnporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<dOperadores> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<dOperadores> operadores) {
        this.operadores = operadores;
    }

}
