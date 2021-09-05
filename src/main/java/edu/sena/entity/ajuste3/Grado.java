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
@Table(name = "tblgrado")
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g")})
public class Grado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idGrado")
    private Integer idGrado;
    @Size(max = 6)
    @Column(name = "tamanoGrado")
    private String tamanoGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrado", fetch = FetchType.LAZY)
    private Collection<ProductoReferencia> productoReferenciaCollection;

    public Grado() {
    }

    public Grado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getTamanoGrado() {
        return tamanoGrado;
    }

    public void setTamanoGrado(String tamanoGrado) {
        this.tamanoGrado = tamanoGrado;
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
        hash += (idGrado != null ? idGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.idGrado == null && other.idGrado != null) || (this.idGrado != null && !this.idGrado.equals(other.idGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.Grado[ idGrado=" + idGrado + " ]";
    }
    
}
