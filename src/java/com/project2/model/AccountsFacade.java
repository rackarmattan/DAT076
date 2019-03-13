package com.project2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade class for account specific queries.
 * @author rackarmattan
 */
@Stateless
public class AccountsFacade extends AbstractFacade<Accounts> {

    @PersistenceContext(unitName = "sample")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFacade() {
        super(Accounts.class);
    }

    public Accounts findByLogin(String login) {
        Accounts acc = null;
        acc = em.createNamedQuery("Accounts.findByLogin", Accounts.class).setParameter("login", login).getSingleResult();
        return acc;
    }
}
