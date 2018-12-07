
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat1Interface extends Remote {
    public void send(String msg) throws RemoteException;
}
