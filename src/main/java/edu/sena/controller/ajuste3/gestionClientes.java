/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Cliente;
import edu.sena.entity.ajuste3.EstadoCliente;
import edu.sena.facade.ajuste3.ClienteFacadeLocal;
import edu.sena.facade.ajuste3.EstadoClienteFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionClientes")
@SessionScoped
public class gestionClientes implements Serializable {
    
    private List Listestados = new ArrayList<>();
    private int numero;
    private String estado;
    private Cliente cliente = new Cliente();
    
    @EJB    
    ClienteFacadeLocal clienteFacadeLocal;

    /**
     * Creates a new instance of gestioClientes
     */
    public gestionClientes() {
    }
    @EJB
    EstadoClienteFacadeLocal estadoClienteFacadeLocal;
    
    @PostConstruct
    public void cargaPostConst() {
        Listestados.addAll(estadoClienteFacadeLocal.findAll());
        
    }
    
    public List<EstadoCliente> listarUsuarios() {
        Listestados = estadoClienteFacadeLocal.findAll();
        return Listestados;
    }
    
    public void registrarCliente() {
        
        System.out.println("ENTRANDO AL MÉTODO");
//        clienteFacadeLocal.create(cliente);
        
    }
    
    public List getEstados() {
        return Listestados;
    }
    
    public void setEstados(List estados) {
        this.Listestados = estados;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
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
}
