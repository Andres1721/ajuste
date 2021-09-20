/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Ventas;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Julian Paredes
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> implements VentasFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Ajuste3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }

    @Override
    public List<Ventas> listarVentas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT g FROM Ventas g");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean registrarVenta(Ventas ventaReg) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_ventas (fecha, nombres, apellidos, direccion, correo, catidad_Articulos, descripcion, precio_total) VALUES (?, ?, ?, ?, ?, ?, ?,?);");

            q.setParameter(1, ventaReg.getFecha());
            q.setParameter(2, ventaReg.getNombres());
            q.setParameter(3, ventaReg.getApellidos());
            q.setParameter(4, ventaReg.getDireccion());
            q.setParameter(5, ventaReg.getCorreo());
            q.setParameter(6, ventaReg.getCatidadArticulos());
            q.setParameter(7, ventaReg.getDescripcion());
            q.setParameter(8, ventaReg.getPrecioTotal());

            q.executeUpdate();
            return TRUE;

        } catch (Exception e) {
            System.out.println("ERROR ::registrarVenta -->" + e.getMessage());
            return FALSE;
        }

    }
    @Override
    public Ventas validarExistencia(String tipo) {
        try {
            Query q = em.createQuery("SELECT c  FROM Ventas c WHERE c.nombres LIKE CONCAT('%',:tipo,'%')");
            q.setParameter("tipo", tipo);
            List<Ventas> listaG = q.getResultList();
            if (listaG.isEmpty()) {
                return null;
            } else {
                return listaG.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

}
