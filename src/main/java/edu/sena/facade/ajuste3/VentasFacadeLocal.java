/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Ventas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface VentasFacadeLocal {

    void create(Ventas ventas);

    void edit(Ventas ventas);

    void remove(Ventas ventas);

    Ventas find(Object id);

    List<Ventas> findAll();

    List<Ventas> findRange(int[] range);

    int count();

    public boolean registrarVenta(Ventas ventaReg);

    public List<Ventas> listarVentas();

    public Ventas validarExistencia(String correoIn);

}
