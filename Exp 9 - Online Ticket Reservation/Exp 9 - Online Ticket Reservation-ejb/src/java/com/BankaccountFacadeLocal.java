/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author William Scott
 */
@Local
public interface BankaccountFacadeLocal {

    void create(Bankaccount bankaccount);

    void edit(Bankaccount bankaccount);

    void remove(Bankaccount bankaccount);

    Bankaccount find(Object id);

    List<Bankaccount> findAll();

    List<Bankaccount> findRange(int[] range);

    int count();
    
}
