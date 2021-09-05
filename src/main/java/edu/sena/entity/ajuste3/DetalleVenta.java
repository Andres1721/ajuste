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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ai00214
 */
@Entity
@Table(name = "tbldetalleventa")
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDetalleVenta")
    private Integer idDetalleVenta;
    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
    @Column(name = "precioUnitario")
    private Integer precioUnitario;
    @Column(name = "valorTotal")
    private Integer valorTotal;
    @Size(max = 45)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenVenta idVenta;
    @JoinColumn(name = "idproductoReferencia", referencedColumnName = "idproductoReferencia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductoReferencia idproductoReferencia;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Integer getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public OrdenVenta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(OrdenVenta idVenta) {
        this.idVenta = idVenta;
    }

    public ProductoReferencia getIdproductoReferencia() {
        return idproductoReferencia;
    }

    public void setIdproductoReferencia(ProductoReferencia idproductoReferencia) {
        this.idproductoReferencia = idproductoReferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleVenta != null ? idDetalleVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.idDetalleVenta == null && other.idDetalleVenta != null) || (this.idDetalleVenta != null && !this.idDetalleVenta.equals(other.idDetalleVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.DetalleVenta[ idDetalleVenta=" + idDetalleVenta + " ]";
    }
    
}
