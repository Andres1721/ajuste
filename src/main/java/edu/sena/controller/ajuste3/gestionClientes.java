/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Cliente;
import edu.sena.entity.ajuste3.EstadoCliente;
import edu.sena.facade.ajuste3.ClienteFacadeLocal;
import edu.sena.facade.ajuste3.DocumentoFacadeLocal;
import edu.sena.facade.ajuste3.EstadoClienteFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionClientes")
@SessionScoped
public class gestionClientes implements Serializable {

    private List ListestadosCli = new ArrayList<>();
    private int numeroEstado;
    private String estado;
    private Cliente cliente = new Cliente();

    @EJB
    EstadoClienteFacadeLocal estadoClienteFacadeLocal;
    @EJB
    ClienteFacadeLocal clienteFacadeLocal;

    /**
     * Creates a new instance of gestioClientes
     */
    public gestionClientes() {
    }

    @PostConstruct
    public void cargaPostConst() {
        ListestadosCli.addAll(estadoClienteFacadeLocal.findAll());

    }

    public List<EstadoCliente> listarUsuarios() {
        ListestadosCli = estadoClienteFacadeLocal.findAll();
        return ListestadosCli;
    }

    public void registrarCliente() {
        EstadoCliente estadoCliente = estadoClienteFacadeLocal.find(numeroEstado);
        cliente.setIdEstadoCliente(estadoCliente);
        clienteFacadeLocal.create(cliente);
        PrimeFaces.current().executeScript("Swal.fire({"
                + "  title: 'Proveedor Registrado con exito!',"
                + "  text: '',"
                + "  icon: 'success',"
                + "  confirmButtonText: 'Ok'"
                + "})");
        cliente = new Cliente();

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroEstado() {
        return numeroEstado;
    }

    public void setNumeroEstado(int numeroEstado) {
        this.numeroEstado = numeroEstado;
    }

    public List getListestadosCli() {
        return ListestadosCli;
    }

    public void setListestadosCli(List ListestadosCli) {
        this.ListestadosCli = ListestadosCli;
    }
}
