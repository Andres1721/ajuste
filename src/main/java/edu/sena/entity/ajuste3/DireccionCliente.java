/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.ajuste3;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ai00214
 */
@Entity
@Table(name = "tbldireccioncliente")
@NamedQueries({
    @NamedQuery(name = "DireccionCliente.findAll", query = "SELECT d FROM DireccionCliente d")})
public class DireccionCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtblDireccionCliente")
    private Integer idtblDireccionCliente;
    @Size(max = 55)
    @Column(name = "direccion")
    private String direccion;
    @JoinColumn(name = "idCiudad", referencedColumnName = "idCiudad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ciudad idCiudad;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;

    public DireccionCliente() {
    }

    public DireccionCliente(Integer idtblDireccionCliente) {
        this.idtblDireccionCliente = idtblDireccionCliente;
    }

    public Integer getIdtblDireccionCliente() {
        return idtblDireccionCliente;
    }

    public void setIdtblDireccionCliente(Integer idtblDireccionCliente) {
        this.idtblDireccionCliente = idtblDireccionCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblDireccionCliente != null ? idtblDireccionCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionCliente)) {
            return false;
        }
        DireccionCliente other = (DireccionCliente) object;
        if ((this.idtblDireccionCliente == null && other.idtblDireccionCliente != null) || (this.idtblDireccionCliente != null && !this.idtblDireccionCliente.equals(other.idtblDireccionCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.DireccionCliente[ idtblDireccionCliente=" + idtblDireccionCliente + " ]";
    }
    
}
