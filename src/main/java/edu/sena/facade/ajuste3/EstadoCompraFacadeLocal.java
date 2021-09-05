/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.EstadoCompra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface EstadoCompraFacadeLocal {

    void create(EstadoCompra estadoCompra);

    void edit(EstadoCompra estadoCompra);

    void remove(EstadoCompra estadoCompra);

    EstadoCompra find(Object id);

    List<EstadoCompra> findAll();

    List<EstadoCompra> findRange(int[] range);

    int count();
    
}
