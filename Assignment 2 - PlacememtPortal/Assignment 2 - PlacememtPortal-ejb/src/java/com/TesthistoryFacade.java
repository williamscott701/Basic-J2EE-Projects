/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author William Scott
 */
@Stateless
public class TesthistoryFacade extends AbstractFacade<Testhistory> implements TesthistoryFacadeLocal {
    @PersistenceContext(unitName = "Assignment_2_-_PlacememtPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TesthistoryFacade() {
        super(Testhistory.class);
    }
    
}
