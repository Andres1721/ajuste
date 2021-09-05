/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Usuario;
import edu.sena.facade.ajuste3.UsuarioFacadeLocal;
import edu.sena.utilidad.Ajuste3.Mail;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionUsuario")
@SessionScoped
public class gestionUsuario implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private Usuario usuReg = new Usuario();
    private Usuario usuLog = new Usuario();
    private Usuario usuTemporal = new Usuario();
    private String mensajes = "";
    private String nombreIn = "";
    private String correoIn = "";
    private String claveIn = "";

    public gestionUsuario() {
    }

    public List<Usuario> listarUsuarios() {
        return usuarioFacadeLocal.listarUsuarios();
    }

    public void validarUsuario() throws IOException {
        usuLog = usuarioFacadeLocal.iniciarSesion(correoIn, claveIn);
        if (usuLog == null) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'Usuario no encontrado',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Intentar de nuevo'"
                    + "})");
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("registrarUsuario.xhtml");

        }

    }

    public void registrarUsuario() {
        System.out.println("Entro alantes");
        if (usuarioFacadeLocal.registrarUsuario(usuReg)) {
            System.out.println("Entro al if");
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'OK!',"
                    + "  text: 'Usuario Registrado',"
                    + "  icon: 'success',"
                    + "  confirmButtonText: 'Aceptar'"
                    + "})");
            System.out.println("Entro al if 1");

        } else {
            System.out.println("Entro al else");
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'Usuario no Registrado',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Intentar de nuevo'"
                    + "})");
            System.out.println("Entro al else 2");
        }

        usuReg = new Usuario();

    }

    public void cerraSesion() throws IOException {
        usuLog = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        fc.getExternalContext().redirect("../index.xhtml");

    }

    public void validarUsusarioActivo() throws IOException {
        if (usuLog == null || usuLog.getCorreoelectronico() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().invalidateSession();
            fc.getExternalContext().redirect("../index.xhtml");
        }
    }

    public void recuperarClave() {
        usuReg = null;
        usuReg = usuarioFacadeLocal.recuperarClave(correoIn);

        if (usuReg != null) {
            try {
                double aleatorio = 100000 * Math.random();
                usuReg.setClave("NC-" + (int) aleatorio + "*@");
                usuarioFacadeLocal.edit(usuReg);
                Mail.recuperarClaves(usuReg.getNombres() + " " + usuReg.getApellidos(), correoIn, usuReg.getClave());
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Correo enviado!',"
                        + "  text: 'Port favor verifique su bandeja de entrada',"
                        + "  icon: 'success',"
                        + "  confirmButtonText: 'Ok'"
                        + "})");
            } catch (Exception e) {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'No se puede realizar esta peticion',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Por favor intente mas tarde'"
                        + "})");

            }

        } else {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'Usuario no encontrado',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Valide su correo Electronico'"
                    + "})");
        }

    }

    public void cambiarEstado(Usuario usu) {
        usuarioFacadeLocal.cambiarEstado(usu);

    }

    public void cargaTemporal(Usuario usuIn) {
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public Usuario getUsuLog() {
        return usuLog;
    }

    public void setUsuLog(Usuario usuLog) {
        this.usuLog = usuLog;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getNombreIn() {
        return nombreIn;
    }

    public void setNombreIn(String nombreIn) {
        this.nombreIn = nombreIn;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }

    public Usuario getUsuTemporal() {
        return usuTemporal;
    }

    public void setUsuTemporal(Usuario usuTemporal) {
        this.usuTemporal = usuTemporal;
    }

}
