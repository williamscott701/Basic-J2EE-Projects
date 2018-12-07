/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author arul
 */
public interface Chat1Interface extends Remote {
    public void send(String msg) throws RemoteException;
}
