/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.OrdenVenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface OrdenVentaFacadeLocal {

    void create(OrdenVenta ordenVenta);

    void edit(OrdenVenta ordenVenta);

    void remove(OrdenVenta ordenVenta);

    OrdenVenta find(Object id);

    List<OrdenVenta> findAll();

    List<OrdenVenta> findRange(int[] range);

    int count();
    
}
