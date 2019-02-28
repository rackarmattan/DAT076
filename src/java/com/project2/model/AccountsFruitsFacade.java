/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rackarmattan
 */
@Stateless
public class AccountsFruitsFacade extends AbstractFacade<AccountsFruits> {

    @PersistenceContext(unitName = "sample")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFruitsFacade() {
        super(AccountsFruits.class);
    }
    
    public List<AccountsFruits> findByLogin(String login) {
        return em.createNamedQuery("AccountsFruits.findByLogin", AccountsFruits.class).setParameter("login", login).getResultList();
    }

}
