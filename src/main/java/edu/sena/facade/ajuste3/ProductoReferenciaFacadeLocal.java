/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.ProductoReferencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface ProductoReferenciaFacadeLocal {

    void create(ProductoReferencia productoReferencia);

    void edit(ProductoReferencia productoReferencia);

    void remove(ProductoReferencia productoReferencia);

    ProductoReferencia find(Object id);

    List<ProductoReferencia> findAll();

    List<ProductoReferencia> findRange(int[] range);

    int count();
    
}
