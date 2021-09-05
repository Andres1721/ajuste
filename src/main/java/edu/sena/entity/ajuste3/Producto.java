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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tblproductos")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInventario")
    private int idInventario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @JoinTable(name = "tblproveedor_abastece_productos", joinColumns = {
        @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")}, inverseJoinColumns = {
        @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Proveedor> proveedorCollection;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor idProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Collection<ProductoReferencia> productoReferenciaCollection;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, int idInventario, String nombreProducto) {
        this.idProducto = idProducto;
        this.idInventario = idInventario;
        this.nombreProducto = nombreProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
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
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
