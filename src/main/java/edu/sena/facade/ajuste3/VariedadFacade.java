/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.ajuste3;

import edu.sena.entity.ajuste3.Variedad;
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
public class VariedadFacade extends AbstractFacade<Variedad> implements VariedadFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Ajuste3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VariedadFacade() {
        super(Variedad.class);
    }

    @Override
    public boolean registrarVariedad(Variedad variedades) {

        try {
            Query q = em.createNativeQuery("INSERT INTO tblvariedades (idVariedades,nombreVariedad,color) VALUES (?, ?, ?);");
            q.setParameter(1, variedades.getIdVariedades());
            q.setParameter(2, variedades.getNombreVariedad());
            q.setParameter(3, variedades.getColor());
            q.executeUpdate();

            return TRUE;
        } catch (Exception e) {
            System.out.println("ERROR ::registrarVariedad -->" + e.getMessage());
            return FALSE;
        }

    }
    
    
    @Override
    public Variedad validarVariedad(String tip) {
        try {
            Query q = em.createQuery("SELECT v FROM Variedad v WHERE v.nombreVariedad LIKE CONCAT('%',:tip,'%')");
            q.setParameter("tip", tip);
            List<Variedad> listaV = q.getResultList();
            if (listaV.isEmpty()) {
                return null;
            } else {
                return listaV.get(0);
            }
        } catch (Exception e) {
            return null;
        }

    }
}
