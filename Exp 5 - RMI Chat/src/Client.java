
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements Chat2Interface {

    public Client() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Registry r = LocateRegistry.createRegistry(32001);
            Client c = new Client();
            r.bind("chat2", c);

            while (true) {
                System.out.print("Chat2:");
                String input = sc.nextLine();
                Registry r1 = LocateRegistry.getRegistry(22000);
                Chat1Interface rob = (Chat1Interface) r1.lookup("chat1");
                rob.send(input);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void send(String msg) throws RemoteException {
        System.out.println("\nChat1: " + msg);
    }
}
