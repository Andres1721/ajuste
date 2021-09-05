/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.OrdenVenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ai00214
 */
@Stateless
public class OrdenVentaFacade extends AbstractFacade<OrdenVenta> implements OrdenVentaFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Ajuste3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenVentaFacade() {
        super(OrdenVenta.class);
    }
    
}
