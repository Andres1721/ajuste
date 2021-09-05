/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.ajuste3;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ai00214
 */
@Entity
@Table(name = "tblestadocliente")
@NamedQueries({
    @NamedQuery(name = "EstadoCliente.findAll", query = "SELECT e FROM EstadoCliente e")})
public class EstadoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoCliente")
    private Integer idEstadoCliente;
    @Size(max = 20)
    @Column(name = "idDescripEstadoCliente")
    private String idDescripEstadoCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCliente", fetch = FetchType.LAZY)
    private Collection<Cliente> clienteCollection;

    public EstadoCliente() {
    }

    public EstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public Integer getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getIdDescripEstadoCliente() {
        return idDescripEstadoCliente;
    }

    public void setIdDescripEstadoCliente(String idDescripEstadoCliente) {
        this.idDescripEstadoCliente = idDescripEstadoCliente;
    }

    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCliente != null ? idEstadoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCliente)) {
            return false;
        }
        EstadoCliente other = (EstadoCliente) object;
        if ((this.idEstadoCliente == null && other.idEstadoCliente != null) || (this.idEstadoCliente != null && !this.idEstadoCliente.equals(other.idEstadoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.EstadoCliente[ idEstadoCliente=" + idEstadoCliente + " ]";
    }
    
}
