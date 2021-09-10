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

//List  <Pais> listapais= new ArrayList<>();
//List  <listciudad> listapais= new ArrayList<>();
//Encapsular las dos listas
//
//@postConstructor
//public  void 
        
