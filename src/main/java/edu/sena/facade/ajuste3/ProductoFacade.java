/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Producto;
import edu.sena.entity.ajuste3.Proveedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


/**
 *
 * @author ai00214
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Ajuste3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
      @Override
    public List<Producto> todosProductos() {
        try {
            Query qp = em.createQuery("SELECT p.idProducto,p.idInventario,p.nombreProducto FROM Productos p");
            return qp.getResultList();

        } catch (Exception e) {
            System.out.println("Error ==>" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Proveedor> listaProveedores() {
        try {
            Query qp = em.createQuery("SELECT p.idProveedor FROM Proveedor p");
            return qp.getResultList();

        } catch (Exception e) {
            System.out.println("Error ==>" + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean registrarProduc(Producto pro) {

        try {
            Query q = em.createNativeQuery("INSERT INTO tblproductos (idProductos, idInventario, nombreProducto, idProveedor) VALUES (?, ?, ?, 2);");
            q.setParameter(1, pro.getIdProducto());
            q.setParameter(2, pro.getIdInventario());
            q.setParameter(3, pro.getNombreProducto());
            q.setParameter(4, pro.getIdProveedor());

            q.executeUpdate();
            return TRUE;

        } catch (Exception e) {
            System.out.println("ERROR ::registrarProduc -->" + e.getMessage());
            return FALSE;
        }

    }
}
