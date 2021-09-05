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
@Table(name = "tbltipoempaque")
@NamedQueries({
    @NamedQuery(name = "TipoEmpaque.findAll", query = "SELECT t FROM TipoEmpaque t")})
public class TipoEmpaque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoEmpaque")
    private Integer idTipoEmpaque;
    @Size(max = 45)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Size(max = 45)
    @Column(name = "descTipoEmpaque")
    private String descTipoEmpaque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cantidadDispo")
    private String cantidadDispo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEmpaque", fetch = FetchType.LAZY)
    private Collection<ProductoReferencia> productoReferenciaCollection;

    public TipoEmpaque() {
    }

    public TipoEmpaque(Integer idTipoEmpaque) {
        this.idTipoEmpaque = idTipoEmpaque;
    }

    public TipoEmpaque(Integer idTipoEmpaque, String cantidadDispo) {
        this.idTipoEmpaque = idTipoEmpaque;
        this.cantidadDispo = cantidadDispo;
    }

    public Integer getIdTipoEmpaque() {
        return idTipoEmpaque;
    }

    public void setIdTipoEmpaque(Integer idTipoEmpaque) {
        this.idTipoEmpaque = idTipoEmpaque;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescTipoEmpaque() {
        return descTipoEmpaque;
    }

    public void setDescTipoEmpaque(String descTipoEmpaque) {
        this.descTipoEmpaque = descTipoEmpaque;
    }

    public String getCantidadDispo() {
        return cantidadDispo;
    }

    public void setCantidadDispo(String cantidadDispo) {
        this.cantidadDispo = cantidadDispo;
    }

    public Collection<ProductoReferencia> getProductoReferenciaCollection() {
        return productoReferenciaCollection;
    }

    public void setProductoReferenciaCollection(Collection<ProductoReferencia> productoReferenciaCollection) {
        this.productoReferenciaCollection = productoReferenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEmpaque != null ? idTipoEmpaque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpaque)) {
            return false;
        }
        TipoEmpaque other = (TipoEmpaque) object;
        if ((this.idTipoEmpaque == null && other.idTipoEmpaque != null) || (this.idTipoEmpaque != null && !this.idTipoEmpaque.equals(other.idTipoEmpaque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.TipoEmpaque[ idTipoEmpaque=" + idTipoEmpaque + " ]";
    }
    
}
