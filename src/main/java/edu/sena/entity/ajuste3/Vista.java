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
@Table(name = "tblvistas")
@NamedQueries({
    @NamedQuery(name = "Vista.findAll", query = "SELECT v FROM Vista v")})
public class Vista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "vis_vistaid")
    private Integer visVistaid;
    @Size(max = 100)
    @Column(name = "vis_nombres")
    private String visNombres;
    @Size(max = 100)
    @Column(name = "vis_ruta")
    private String visRuta;
    @Column(name = "vis_estado")
    private Short visEstado;
    @JoinColumn(name = "fk_rolid", referencedColumnName = "rol_rolid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol fkRolid;

    public Vista() {
    }

    public Vista(Integer visVistaid) {
        this.visVistaid = visVistaid;
    }

    public Integer getVisVistaid() {
        return visVistaid;
    }

    public void setVisVistaid(Integer visVistaid) {
        this.visVistaid = visVistaid;
    }

    public String getVisNombres() {
        return visNombres;
    }

    public void setVisNombres(String visNombres) {
        this.visNombres = visNombres;
    }

    public String getVisRuta() {
        return visRuta;
    }

    public void setVisRuta(String visRuta) {
        this.visRuta = visRuta;
    }

    public Short getVisEstado() {
        return visEstado;
    }

    public void setVisEstado(Short visEstado) {
        this.visEstado = visEstado;
    }

    public Rol getFkRolid() {
        return fkRolid;
    }

    public void setFkRolid(Rol fkRolid) {
        this.fkRolid = fkRolid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visVistaid != null ? visVistaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vista)) {
            return false;
        }
        Vista other = (Vista) object;
        if ((this.visVistaid == null && other.visVistaid != null) || (this.visVistaid != null && !this.visVistaid.equals(other.visVistaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.alterno.Vista[ visVistaid=" + visVistaid + " ]";
    }
    
}
