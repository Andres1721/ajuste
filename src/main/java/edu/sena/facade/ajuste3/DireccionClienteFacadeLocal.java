/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.DireccionCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface DireccionClienteFacadeLocal {

    void create(DireccionCliente direccionCliente);

    void edit(DireccionCliente direccionCliente);

    void remove(DireccionCliente direccionCliente);

    DireccionCliente find(Object id);

    List<DireccionCliente> findAll();

    List<DireccionCliente> findRange(int[] range);

    int count();
    
}
