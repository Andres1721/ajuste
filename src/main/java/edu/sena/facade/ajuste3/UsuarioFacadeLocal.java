/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai00214
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    public boolean registrarUsuario(Usuario usuReg);

    public Usuario iniciarSesion(String correoIn, String claveIn);

    public Usuario recuperarClave(String correoIn);

    public List<Usuario> listarUsuarios();

    public boolean cambiarEstado(Usuario usuReg);

    int count();

}
