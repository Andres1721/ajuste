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
@Table(name = "tblformapago")
@NamedQueries({
    @NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f")})
public class FormaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFormaPago")
    private Integer idFormaPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DrescripFormaPago")
    private String drescripFormaPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaPago", fetch = FetchType.LAZY)
    private Collection<OrdenVenta> ordenVentaCollection;

    public FormaPago() {
    }

    public FormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public FormaPago(Integer idFormaPago, String drescripFormaPago) {
        this.idFormaPago = idFormaPago;
        this.drescripFormaPago = drescripFormaPago;
    }

    public Integer getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getDrescripFormaPago() {
        return drescripFormaPago;
    }

    public void setDrescripFormaPago(String drescripFormaPago) {
        this.drescripFormaPago = drescripFormaPago;
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
        hash += (idFormaPago != null ? idFormaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.idFormaPago == null && other.idFormaPago != null) || (this.idFormaPago != null && !this.idFormaPago.equals(other.idFormaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.FormaPago[ idFormaPago=" + idFormaPago + " ]";
    }
    
}
