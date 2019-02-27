/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
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

    //@NamedQuery(name = "Accounts.findByLogin", query = "SELECT a FROM Accounts a WHERE a.login = :login")
    public List<Accounts> findByLogin(String login) {
        return em.createNamedQuery("Accounts.findByLogin", Accounts.class).setParameter("login", login).getResultList();
    }
}
