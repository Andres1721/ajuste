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
@Table(name = "tblestadocompra")
@NamedQueries({
    @NamedQuery(name = "EstadoCompra.findAll", query = "SELECT e FROM EstadoCompra e")})
public class EstadoCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoCompra")
    private Integer idEstadoCompra;
    @Size(max = 15)
    @Column(name = "descripEstadoPago")
    private String descripEstadoPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCompra", fetch = FetchType.LAZY)
    private Collection<OrdenVenta> ordenVentaCollection;

    public EstadoCompra() {
    }

    public EstadoCompra(Integer idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public Integer getIdEstadoCompra() {
        return idEstadoCompra;
    }

    public void setIdEstadoCompra(Integer idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public String getDescripEstadoPago() {
        return descripEstadoPago;
    }

    public void setDescripEstadoPago(String descripEstadoPago) {
        this.descripEstadoPago = descripEstadoPago;
    }

    public Collection<OrdenVenta> getOrdenVentaCollection() {
        return ordenVentaCollection;
    }

    public void setOrdenVentaCollection(Collection<OrdenVenta> ordenVentaCollection) {
        this.ordenVentaCollection = ordenVentaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCompra != null ? idEstadoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCompra)) {
            return false;
        }
        EstadoCompra other = (EstadoCompra) object;
        if ((this.idEstadoCompra == null && other.idEstadoCompra != null) || (this.idEstadoCompra != null && !this.idEstadoCompra.equals(other.idEstadoCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.EstadoCompra[ idEstadoCompra=" + idEstadoCompra + " ]";
    }
    
}
