/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.TipoEmpaque;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface TipoEmpaqueFacadeLocal {

    void create(TipoEmpaque tipoEmpaque);

    void edit(TipoEmpaque tipoEmpaque);

    void remove(TipoEmpaque tipoEmpaque);

    TipoEmpaque find(Object id);

    List<TipoEmpaque> findAll();

    List<TipoEmpaque> findRange(int[] range);

    int count();
    
}
