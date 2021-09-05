/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.ajuste3;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ai00214
 */
@Entity
@Table(name = "tblordenventa")
@NamedQueries({
    @NamedQuery(name = "OrdenVenta.findAll", query = "SELECT o FROM OrdenVenta o")})
public class OrdenVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVenta")
    private Integer idVenta;
    @Column(name = "fechaVentaFacturacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVentaFacturacion;
    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "valorTotal")
    private Integer valorTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta", fetch = FetchType.LAZY)
    private Collection<DetalleVenta> detalleVentaCollection;
    @JoinColumn(name = "idEstadoCompra", referencedColumnName = "idEstadoCompra")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoCompra idEstadoCompra;
    @JoinColumn(name = "idFormaPago", referencedColumnName = "idFormaPago")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FormaPago idFormaPago;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;

    public OrdenVenta() {
    }

    public OrdenVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public OrdenVenta(Integer idVenta, String observaciones) {
        this.idVenta = idVenta;
        this.observaciones = observaciones;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVentaFacturacion() {
        return fechaVentaFacturacion;
    }

    public void setFechaVentaFacturacion(Date fechaVentaFacturacion) {
        this.fechaVentaFacturacion = fechaVentaFacturacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
    }

    public EstadoCompra getIdEstadoCompra() {
        return idEstadoCompra;
    }

    public void setIdEstadoCompra(EstadoCompra idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public FormaPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(FormaPago idFormaPago) {
        this.idFormaPago = idFormaPago;
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
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenVenta)) {
            return false;
        }
        OrdenVenta other = (OrdenVenta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ajuste3.OrdenVenta[ idVenta=" + idVenta + " ]";
    }
    
}
