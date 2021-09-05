/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Variedad;
import edu.sena.facade.ajuste3.VariedadFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionVariedades")
@SessionScoped
public class gestionVariedades implements Serializable {

    private Variedad varReg = new Variedad();
    private String mensajes = "";
    @EJB
    VariedadFacadeLocal variedadFacadeLocal;

    /**
     * Creates a new instance of gestionVariedades
     */
    public gestionVariedades() {
    }

    public List<Variedad> todasVariedades() {
        return variedadFacadeLocal.findAll();
    }

    public void crearVariedad() {

        if (variedadFacadeLocal.registrarVariedad(varReg)) {
            mensajes = "regOk";
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Variedad Registrada!',"
                    + "  text: 'Registro Exito',"
                    + "  icon: 'success',"
                    + "  confirmButtonText: 'Ok'"
                    + "})");
        } else {
            mensajes = "regBad";
             PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'Verificar registro de Variedad',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Por favor intente mas tarde'"
                        + "})");

        }

        varReg = new Variedad();

    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public Variedad getVarReg() {
        return varReg;
    }

    public void setVarReg(Variedad varReg) {
        this.varReg = varReg;
    }

}
