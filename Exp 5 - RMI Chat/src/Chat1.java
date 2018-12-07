
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Chat1 extends UnicastRemoteObject implements Chat1Interface {

    public Chat1() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.createRegistry(22000);
            Chat1 c = new Chat1();
            r.bind("chat1", c);

            while (true) {
                System.out.print("Chat 1 ...:");
            Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                Registry r1 = LocateRegistry.getRegistry(32001);
                Chat2Interface rob = (Chat2Interface) r1.lookup("chat2");
                rob.send(input);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void send(String msg) throws RemoteException {
        System.out.println("\nChat 2 ...: " + msg);
    }
}
