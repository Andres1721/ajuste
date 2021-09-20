/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Producto;
import edu.sena.entity.ajuste3.Proveedor;
import edu.sena.facade.ajuste3.ProductoFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionProductos")
@ViewScoped
public class gestionProductos implements Serializable {

    private String mensajes;
    private Producto pro = new Producto();
    private Proveedor prove = new Proveedor();
    @EJB
    ProductoFacadeLocal productoFacadeLocal;
    @EJB
    ProductoFacadeLocal ProductosFacadeLocal;
    @Resource(lookup = "java:app/ajuste3")
    DataSource dataSource;

    /**
     * Creates a new instance of gestionProductos
     */
    public gestionProductos() {

    }

    public List<Producto> todosProductos() {
        return ProductosFacadeLocal.findAll();
    }

    public List<Proveedor> todosProveedores() {
        return productoFacadeLocal.listaProveedores();
    }

    public void crearProducto() {
//        productoFacadeLocal.create(pro);
//        mensajes = "RegistOK";
//        pro = new Productos();

        productoFacadeLocal.registrarProduc(pro);
        mensajes = "regOk   ";

    }

    public void descargaReporte() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try {
            File jasper = new File(context.getRealPath("/reportes/productosPorProveedor.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=productosPorProveedor.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (IOException | SQLException | JRException e) {
            System.out.println("gestionUsuario.descargaReporte() " + e.getMessage());

        }
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public Proveedor getProve() {
        return prove;
    }

    public void setProve(Proveedor prove) {
        this.prove = prove;
    }

}
