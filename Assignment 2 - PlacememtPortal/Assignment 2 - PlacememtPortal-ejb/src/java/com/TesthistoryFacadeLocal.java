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
public interface TesthistoryFacadeLocal {

    void create(Testhistory testhistory);

    void edit(Testhistory testhistory);

    void remove(Testhistory testhistory);

    Testhistory find(Object id);

    List<Testhistory> findAll();

    List<Testhistory> findRange(int[] range);

    int count();
    
}
