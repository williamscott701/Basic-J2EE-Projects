
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIClientMain extends UnicastRemoteObject {

    public RMIClientMain() throws RemoteException {
    }

    public static void main(String[] args) throws Exception {
        try {
            
            Scanner in = new Scanner(System.in);
            CeaserCipher cc = new CeaserCipher();
            Registry r1 = LocateRegistry.getRegistry(22004);
            RMIServerInterface rob = (RMIServerInterface) r1.lookup("server");
            System.out.println("\nSucessfully Connected to Server");

            while (true) {
                System.out.println("\n1. Show Indexes of Files\n2. Get File Using Index\n3. Change Encryption Key\n4. Exit\n");
                switch (in.nextLine()) {
                    case "1":
                        System.out.println("\nFiles Available:");
                        System.out.println(rob.showAvailableFiles());
                        break;
                    case "2":
                        System.out.println("\nEnter File Index Number: ");
                        String temp = rob.getFileData(in.nextLine());
                        System.out.println("\nReceived Encrypted Message: " + temp);
                        System.out.println("Sent for Decryption");
                        temp = cc.decode(temp);
                        System.out.println("Decrpted Message: " + temp);
                        break;
                    case "3":
                        cc.setkey();
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid Option! Try Again...");
                        break;
                }
            }

        } catch (RemoteException | NotBoundException e) {
            System.out.println(e);
        }

    }
}
