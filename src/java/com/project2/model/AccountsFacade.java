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
        List l = new ArrayList<>();
        try {
             l = em.createNamedQuery("Accounts.findByLogin").setParameter("login", login).getResultList();
             //more than one with the same login
             if(l.size() > 1){
                 throw new IllegalStateException("More than one account with the same login");
             }
        } catch (EJBException e) {
            throw new IllegalStateException("EJBexception kastat :(");
        }
        finally{
            return l;
        }
    }

}
