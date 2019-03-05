/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    
    public Set<Fruits> findAccountFruits(String login){
        Accounts ac = em.find(Accounts.class, login);
        if(ac != null){
            System.out.println("Inne i if-sats, ac ej null");
            return ac.getFruits();
        }
        return null;
    }
}
