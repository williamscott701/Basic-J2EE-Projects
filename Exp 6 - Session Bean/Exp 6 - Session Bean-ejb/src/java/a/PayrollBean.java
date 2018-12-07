package a;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class PayrollBean implements PayrollBeanLocal {

    JavaClass jc;

    public PayrollBean() throws Exception {
        this.jc = new JavaClass();
    }

    @Override
    public String signin(String username, String password) {
        try {
            return jc.signin(username, password);
        } catch (Exception ex) {
            Logger.getLogger(PayrollBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failed";
    }

    @Override
    public String signup(String name, String username, String email, String dob, String password) {
        try {
            return jc.signup(name, username, email, dob, password);
        } catch (Exception ex) {
            Logger.getLogger(PayrollBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failed";
    }

    @Override
    public HashMap getPayroll(String username) {
        try {
            return jc.getPayroll(username);
        } catch (Exception ex) {
            Logger.getLogger(PayrollBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new HashMap();
    }

    @Override
    public boolean setPayDetails(String username, double bp, double hra, double tax) {
        try {
            return jc.addDetails(username, bp, hra, tax);
        } catch (Exception ex) {
            Logger.getLogger(PayrollBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
