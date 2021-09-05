/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.EstadoCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface EstadoClienteFacadeLocal {

    void create(EstadoCliente estadoCliente);

    void edit(EstadoCliente estadoCliente);

    void remove(EstadoCliente estadoCliente);

    EstadoCliente find(Object id);

    List<EstadoCliente> findAll();

    List<EstadoCliente> findRange(int[] range);

    int count();
    
}
