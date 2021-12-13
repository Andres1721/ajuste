/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Rol;
import edu.sena.entity.ajuste3.Usuario;
import edu.sena.facade.ajuste3.RolFacadeLocal;
import edu.sena.facade.ajuste3.UsuarioFacadeLocal;
import edu.sena.utilidad.Ajuste3.Mail;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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

    @EJB
    RolFacadeLocal rolFacadeLocal;

    private Usuario usuReg = new Usuario();
    private Usuario usuLog = new Usuario();
    private Usuario usuTemporal = new Usuario();
    private String mensajes = "";
    private String nombreIn = "";
    private String correoIn = "";
    private String claveIn = "";

//    Infolefts
    private long cantidadCli = 0;
    private long cantidadProveedores = 0;
    private long cantidadMonto = 0;

    private List<Rol> todosLosRoles = new ArrayList<>();
    private List<Rol> rolesSinAsignar = new ArrayList<>();
    @Resource(lookup = "java:app/ajuste3")
    DataSource dataSource;

    public gestionUsuario() {
    }

    @PostConstruct
    public void cargaInicial() {
        todosLosRoles.addAll(rolFacadeLocal.findAll());
        cantidadCli = usuarioFacadeLocal.cantidadClientes();
        cantidadProveedores = usuarioFacadeLocal.cantidadProveedores();
        cantidadMonto =usuarioFacadeLocal.cantidadMontoFacturas();
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
            fc.getExternalContext().redirect("usuario/index.xhtml");

        }

    }

    public void registrarUsuario() {
        try {
            Usuario usuExist = usuarioFacadeLocal.recuperarClave(usuReg.getCorreoelectronico());
            if (usuExist == null || usuExist.getCorreoelectronico() == null) {
                if (usuarioFacadeLocal.registrarUsuario(usuReg)) {
                    usuReg = new Usuario();
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'OK!',"
                            + "  text: 'Usuario Registrado',"
                            + "  icon: 'success',"
                            + "  confirmButtonText: 'Aceptar'"
                            + "})");
                } else {

                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'Usuario no Registrado',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Intentar de nuevo'"
                            + "})");
                }

            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'Usuario ya está Registrado',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Recuperar su clave'"
                        + "})");
            }
        } catch (Exception e) {
        }
    }

    public void cerraSesion() throws IOException {
        usuLog = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        fc.getExternalContext().redirect("../index.xhtml");

    }

    public void validarUsuarioActivo() throws IOException {
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
                        + "  text: '',"
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

    public void descargaReporte() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try {
            File jasper = new File(context.getRealPath("/reportes/usuarios.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=usuarios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (IOException | SQLException | JRException e) {
            System.out.println("gestionUsuario.descargaReporte() " + e.getMessage());

        }
    }

    public void actualizarMisDatos() {
        try {

            usuarioFacadeLocal.actualizarDatosPerfil(usuLog);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Usuario Actualizado !',"
                    + "  text: 'Con Exito !!!',"
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

    }

    public void cambiarEstado(Usuario usu) {
        usuarioFacadeLocal.cambiarEstado(usu);

    }

    public void llamadoProcedimiento() {
        System.out.println("Llamando el  procedimiento");
        usuarioFacadeLocal.llamadoProcedure();

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

    public List<Rol> getTodosLosRoles() {
        return todosLosRoles;
    }

    public void setTodosLosRoles(List<Rol> todosLosRoles) {
        this.todosLosRoles = todosLosRoles;
    }

    public List<Rol> getRolesSinAsignar() {
        return rolesSinAsignar;
    }

    public void setRolesSinAsignar(List<Rol> rolesSinAsignar) {
        this.rolesSinAsignar = rolesSinAsignar;
    }

    public long getCantidadCli() {
        return cantidadCli;
    }

    public void setCantidadCli(long cantidadCli) {
        this.cantidadCli = cantidadCli;
    }

    public long getCantidadProveedores() {
        return cantidadProveedores;
    }

    public void setCantidadProveedores(long cantidadProveedores) {
        this.cantidadProveedores = cantidadProveedores;
    }

    public long getCantidadMonto() {
        return cantidadMonto;
    }

    public void setCantidadMonto(long cantidadMonto) {
        this.cantidadMonto = cantidadMonto;
    }

}
