package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class JavaClass {

    Connection con;
    String return_value;

    public JavaClass() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
    }

    public String signin(String username, String password) throws Exception {
        String query = "select name from WS.REGISTERED_USERS where USERNAME='" + username + "' and PASSWORD='" + password.hashCode() + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return_value = "failed";
        if (rs.next()) {
            return_value = rs.getString("name");
        }
        return return_value;
    }

    public String signup(String name, String username, String email, String dob, String pwd) throws Exception {
        String query = "insert into ws.Registered_users values('" + name + "', '" + username + "', '" + email + "', '" + dob + "', '" + pwd.hashCode() + "')";
        Statement st = con.createStatement();
        int a = st.executeUpdate(query);
        return_value = "failed";
        if (a == 1) {
            return_value = name;
        }
        return return_value;
    }

    public HashMap getPayroll(String username) throws Exception {
        String query = "select * from Employee_pay where USERNAME='" + username + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return_value = "failed";
        HashMap hm = new HashMap();
        if (rs.next()) {
            double bp = Double.parseDouble(rs.getString("basic_pay"));
            double hra = Double.parseDouble(rs.getString("hra"));
            double tax = Double.parseDouble(rs.getString("tax"));
            double grosspay = bp + hra;
            double netsalary = grosspay - tax;
            hm.put("Basic Pay", bp);
            hm.put("HRA", hra);
            hm.put("Tax", tax);
            hm.put("Gross Pay", grosspay);
            hm.put("Net Salary", netsalary);
        }
        return hm;
    }

    public boolean addDetails(String username, double bp, double hra, double tax) throws Exception {
        String query = "select * from Employee_pay where USERNAME='" + username + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Boolean flag = false;
        if (rs.next()) {
            query = "update employee_pay set basic_pay=" + bp + ", hra=" + hra + ", tax=" + tax + " where username='" + username + "'";
            Statement st2 = con.createStatement();
            int i = st2.executeUpdate(query);
            if (i == 1) {
                flag = true;
            }
        } else {
            query = "insert into employee_pay values('" + username + "', " + bp + ", " + hra + ", " + tax + ")";
            Statement st3 = con.createStatement();
            int a = st3.executeUpdate(query);
            if (a == 1) {
                flag = true;
            }
        }
        return flag;
    }
}
