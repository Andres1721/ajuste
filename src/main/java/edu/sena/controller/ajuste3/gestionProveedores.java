/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Proveedor;
import edu.sena.facade.ajuste3.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionProveedores")
@ViewScoped
public class gestionProveedores implements Serializable {

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;

    public gestionProveedores() {
    }
    
        public List<Proveedor> listarProveedores() {
        return proveedorFacadeLocal.findAll();
    }

}
