/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
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
    
}
