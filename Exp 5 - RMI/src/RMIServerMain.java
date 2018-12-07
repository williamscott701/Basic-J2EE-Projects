
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Scanner;

public class RMIServerMain extends UnicastRemoteObject implements RMIServerInterface {

    public static int op, sno, j;
    public static Scanner in = new Scanner(System.in);
    public static HashMap hm = new HashMap();
    public static CeaserCipher cc = new CeaserCipher();

    public RMIServerMain() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            hm.put("1", "William Scott developed this");
            hm.put("2", "hello world program");
            hm.put("3", "Java is awesome");
            hm.put("4", "Sample Text paragraph");
            hm.put("5", "This is just a sample paragraph");
            Registry r = LocateRegistry.createRegistry(22004);
            RMIServerMain s = new RMIServerMain();
            r.bind("server", s);
            System.out.println("\nServer Bind Complete!...");
            while (true) {
                System.out.println("\n" + hm);
                System.out.println("\n1. Add Data\n2. Remove Data\n3. Change Encryption Key\n");
                switch (in.nextLine()) {
                    case "1":
                        System.out.println("\nEnter Key: ");
                        String key = in.nextLine();
                        System.out.println("Enetr Value: ");
                        String value = in.nextLine();
                        hm.put(key, value);
                        break;
                    case "2":
                        System.out.println("\nEnter Key: ");
                        String k = in.nextLine();
                        hm.remove(k);
                        break;
                    case "3":
                        cc.setkey();
                        break;
                    default:
                        System.out.println("\nInvalid Option! Try Again....");
                        break;
                }
            }
        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public String showAvailableFiles() throws RemoteException {
        System.out.println("Request for files received");
        System.out.println("Files list sent\n");
        return hm.keySet().toString();
    }

    @Override
    public String getFileData(String index) throws RemoteException {
        System.out.println("Request for file received with index: " + index);
        System.out.println("Sending file data for encryption\n");
        return cc.encode((String) hm.get(index));
    }
}
