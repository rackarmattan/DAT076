package com.project2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade class for fruit specific queries.
 * @author rackarmattan
 */
@Stateless
public class FruitsFacade extends AbstractFacade<Fruits> {

    @PersistenceContext(unitName = "sample")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FruitsFacade() {
        super(Fruits.class);
    }
    
    public Fruits findByFname(String fname) {
        Fruits fruit = null;
        fruit = em.createNamedQuery("Fruits.findByFname", Fruits.class).setParameter("fname", fname).getSingleResult();
        return fruit;
    }
}
