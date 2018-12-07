package LibraryAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaClassGetAll {

    public static String GetAllData() throws ClassNotFoundException, SQLException, Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
        String query = "select * from BOOKS where count>0";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        String out = "[";
        while (rs.next()) {
            out += "{\"id\":\"" + rs.getString(1) + "\", \"name\":\"" + rs.getString(2) + "\", \"count\":\"" + rs.getString(3) + "\"}, ";
        }
        out = out.substring(0, out.length() - 2);
        out += "]";
        return out;
    }
}
