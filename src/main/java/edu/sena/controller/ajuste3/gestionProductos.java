/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Producto;
import edu.sena.entity.ajuste3.Proveedor;
import edu.sena.facade.ajuste3.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
