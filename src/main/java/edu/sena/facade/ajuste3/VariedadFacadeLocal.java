/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Variedad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface VariedadFacadeLocal {

    void create(Variedad variedad);

    void edit(Variedad variedad);

    void remove(Variedad variedad);

    Variedad find(Object id);

    List<Variedad> findAll();

    List<Variedad> findRange(int[] range);

    int count();

    public boolean registrarVariedad(Variedad variedades);

    public Variedad validarVariedad(String tipo);
}
