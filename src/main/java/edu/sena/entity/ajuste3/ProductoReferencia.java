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
@Table(name = "tblproductoreferencia")
@NamedQueries({
    @NamedQuery(name = "ProductoReferencia.findAll", query = "SELECT p FROM ProductoReferencia p")})
public class ProductoReferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproductoReferencia")
    private Integer idproductoReferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "precio")
    private String precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cantDispo")
    private String cantDispo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproductoReferencia", fetch = FetchType.LAZY)
    private Collection<DetalleVenta> detalleVentaCollection;
    @JoinColumn(name = "idGrado", referencedColumnName = "idGrado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado idGrado;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto idProducto;
    @JoinColumn(name = "idTipoEmpaque", referencedColumnName = "idTipoEmpaque")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoEmpaque idTipoEmpaque;
    @JoinColumn(name = "idVariedades", referencedColumnName = "idVariedades")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Variedad idVariedades;

    public ProductoReferencia() {
    }

    public ProductoReferencia(Integer idproductoReferencia) {
        this.idproductoReferencia = idproductoReferencia;
    }

    public ProductoReferencia(Integer idproductoReferencia, String nombre, String descripcion, String precio, String cantDispo) {
        this.idproductoReferencia = idproductoReferencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantDispo = cantDispo;
    }

    public Integer getIdproductoReferencia() {
        return idproductoReferencia;
    }

    public void setIdproductoReferencia(Integer idproductoReferencia) {
        this.idproductoReferencia = idproductoReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantDispo() {
        return cantDispo;
    }

    public void setCantDispo(String cantDispo) {
        this.cantDispo = cantDispo;
    }

    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public TipoEmpaque getIdTipoEmpaque() {
        return idTipoEmpaque;
    }

    public void setIdTipoEmpaque(TipoEmpaque idTipoEmpaque) {
        this.idTipoEmpaque = idTipoEmpaque;
    }

    public Variedad getIdVariedades() {
        return idVariedades;
    }

    public void setIdVariedades(Variedad idVariedades) {
        this.idVariedades = idVariedades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductoReferencia != null ? idproductoReferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoReferencia)) {
            return false;
        }
        ProductoReferencia other = (ProductoReferencia) object;
        if ((this.idproductoReferencia == null && other.idproductoReferencia != null) || (this.idproductoReferencia != null && !this.idproductoReferencia.equals(other.idproductoReferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.ProductoReferencia[ idproductoReferencia=" + idproductoReferencia + " ]";
    }
    
}
