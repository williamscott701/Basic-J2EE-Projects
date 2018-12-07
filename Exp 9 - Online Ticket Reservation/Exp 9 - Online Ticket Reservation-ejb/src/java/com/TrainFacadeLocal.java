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
public interface TrainFacadeLocal {

    void create(Train train);

    void edit(Train train);

    void remove(Train train);

    Train find(Object id);

    List<Train> findAll();

    List<Train> findRange(int[] range);

    int count();
    
}
