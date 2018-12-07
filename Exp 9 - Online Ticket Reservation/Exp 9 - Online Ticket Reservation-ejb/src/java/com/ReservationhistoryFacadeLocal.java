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
public interface ReservationhistoryFacadeLocal {

    void create(Reservationhistory reservationhistory);

    void edit(Reservationhistory reservationhistory);

    void remove(Reservationhistory reservationhistory);

    Reservationhistory find(Object id);

    List<Reservationhistory> findAll();

    List<Reservationhistory> findRange(int[] range);

    int count();
    
}
