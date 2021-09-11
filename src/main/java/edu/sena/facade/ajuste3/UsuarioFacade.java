/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Usuario;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ai00214
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Ajuste3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public boolean registrarUsuario(Usuario usuReg) {
        System.out.println("Entro a registrar");

        try {
            Query q = em.createNativeQuery("INSERT INTO tblusuario ( usuarioid,tipoDocumento,numerodocumento,nombres, apellidos, correoelectronico,  clave) VALUES (?,?, ?, ?, ?, ?, ?);");
            q.setParameter(1, usuReg.getUsuarioid());
            q.setParameter(2, usuReg.getTipoDocumento());
            q.setParameter(3, usuReg.getNumerodocumento());
            q.setParameter(4, usuReg.getNombres());
            q.setParameter(5, usuReg.getApellidos());
            q.setParameter(6, usuReg.getCorreoelectronico());
            q.setParameter(7, usuReg.getClave());

            q.executeUpdate();
            return TRUE;

        } catch (Exception e) {
            System.out.println("ERROR ::registrarUsuario -->" + e.getMessage());
            return FALSE;
        }

    }

    @Override
    public Usuario iniciarSesion(String correoIn, String claveIn) {

        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico =:correoIn AND u.clave =:claveIn");
            qt.setParameter("correoIn", correoIn);
            qt.setParameter("claveIn", claveIn);
            return (Usuario) qt.getSingleResult();

        } catch (Exception e) {
            System.out.println("ERROR ::iniciarSesion -->" + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario recuperarClave(String correoIn) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico = :correoIn");
            q.setParameter("correoIn", correoIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public Usuario exitenciadeId(int id) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioid = :id");
            q.setParameter("id", id);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Usuario> listarUsuarios() {

        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Error  en :: listaUsuarios " + e.getMessage());
            return null;

        }

    }

    @Override
    public boolean cambiarEstado(Usuario usu) {

        try {
            if (usu.getEstado()) {
                usu.setEstado(Boolean.FALSE);
                System.out.println("Entro al if");
            } else {
                usu.setEstado(Boolean.TRUE);
                System.out.println("Entro al else");

            }
            System.out.println("Entro al facade");
            Query qt = em.createQuery("UPDATE Usuario p SET p.estado= :estadoNuevo WHERE p.usuarioid = :usuId");
            qt.setParameter("estadoNuevo", usu.getEstado());
            qt.setParameter("usuId", usu.getUsuarioid());
            qt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error usuarioFaca::cambiar estadode");
            return false;

        }
    }
}
