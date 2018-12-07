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
public class BankaccountFacade extends AbstractFacade<Bankaccount> implements BankaccountFacadeLocal {
    @PersistenceContext(unitName = "Exp_9_-_Online_Ticket_Reservation-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BankaccountFacade() {
        super(Bankaccount.class);
    }
    
}
