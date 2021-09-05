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
@Table(name = "tblvariedades")
@NamedQueries({
    @NamedQuery(name = "Variedad.findAll", query = "SELECT v FROM Variedad v")})
public class Variedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVariedades")
    private Integer idVariedades;
    @Size(max = 45)
    @Column(name = "nombreVariedad")
    private String nombreVariedad;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVariedades", fetch = FetchType.LAZY)
    private Collection<ProductoReferencia> productoReferenciaCollection;

    public Variedad() {
    }

    public Variedad(Integer idVariedades) {
        this.idVariedades = idVariedades;
    }

    public Integer getIdVariedades() {
        return idVariedades;
    }

    public void setIdVariedades(Integer idVariedades) {
        this.idVariedades = idVariedades;
    }

    public String getNombreVariedad() {
        return nombreVariedad;
    }

    public void setNombreVariedad(String nombreVariedad) {
        this.nombreVariedad = nombreVariedad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        hash += (idVariedades != null ? idVariedades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variedad)) {
            return false;
        }
        Variedad other = (Variedad) object;
        if ((this.idVariedades == null && other.idVariedades != null) || (this.idVariedades != null && !this.idVariedades.equals(other.idVariedades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.Variedad[ idVariedades=" + idVariedades + " ]";
    }
    
}
