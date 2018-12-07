
import java.util.Scanner;

public class CeaserCipher {

    public int op, sno, j;
    public Scanner in = new Scanner(System.in);

    public CeaserCipher() {
        setkey();
    }

    public String encode(String text) {
        System.out.println("\n---------Encoding Request Received---------");
        System.out.println("Input: " + text);
        if (op == 1) {
            return left(text);
        } else if (op == 2) {
            return right(text);
        }
        return "";
    }

    public String decode(String text) {
        System.out.println("\n---------Decoding Request Received---------");
        System.out.println("Input: " + text);
        if (op == 2) {
            return left(text);
        } else if (op == 1) {
            return right(text);
        }
        return "";
    }

    public void setkey() {
        System.out.println("---------Set Key--------");
        int i = 0;
        do {
            if (i != 0) {
                System.out.println("Invalid Option!, Try Again");
            }
            System.out.println("\nSelect Shift Operation: \n1.Left    2.Right");
            op = in.nextInt();
            in.nextLine();
            i++;
        } while (op != 1 && op != 2);
        System.out.print("\nEnter Shift Number: ");
        sno = in.nextInt();
        in.nextLine();
    }

    public String left(String text) {
        String output = "";

        for (char i : text.toCharArray()) {
            j = i;
            if (Character.isLowerCase(i)) {
                j = i - sno;
                if (j < 'a') {
                    j += 26;
                }
            }

            if (Character.isUpperCase(i)) {
                j = i - sno;
                if (j < 'A') {
                    j += 26;
                }
            }
            output = output + (char) j;
        }
        System.out.println("Output: " + output + "\n");
        return output;
    }

    public String right(String text) {
        String output = "";
        for (char i : text.toCharArray()) {
            if (Character.isLowerCase(i)) {
                output = output + (char) ((i - 'a' + sno) % 26 + 'a');
            } else if (Character.isUpperCase(i)) {
                output = output + (char) ((i - 'A' + sno) % 26 + 'A');
            } else {
                output = output + i;
            }
        }
        System.out.println("Output: " + output + "\n");
        return output;
    }
}