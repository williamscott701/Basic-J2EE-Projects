
package a;

import java.util.HashMap;
import javax.ejb.Local;

@Local
public interface PayrollBeanLocal {

    String signin(String username, String password);

    String signup(String name, String username, String email, String dob, String password);

    HashMap getPayroll(String username);

    boolean setPayDetails(String username, double bp, double hra, double tax);

}
