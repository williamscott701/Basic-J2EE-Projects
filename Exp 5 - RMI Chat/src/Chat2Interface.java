
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat2Interface extends Remote {
    public void send(String msg) throws RemoteException;
}