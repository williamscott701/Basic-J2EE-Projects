
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote{
    public String showAvailableFiles() throws RemoteException;
    public String getFileData(String index) throws RemoteException;
}
