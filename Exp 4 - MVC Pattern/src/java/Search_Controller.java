
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;

public class Search_Controller {

    public Search_Controller() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
    }

    public HashSet suggestTablets(String symlist) throws Exception {
        String sym_search;
        SortedMap tablets_for_symptoms = new TreeMap();
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
        HashSet suggested_tablets = new HashSet();
        HashSet sym_list_str_array = new HashSet(Arrays.asList(symlist.split(" ")));
        while (!sym_list_str_array.isEmpty()) {
            tablets_for_symptoms.clear();
            for (Object sym_list_single : sym_list_str_array) {
                System.out.println(sym_list_single);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT tabletname from WS.TABLETS where tabletuse='" + sym_list_single + "'");
                while (rs.next()) {
                    sym_search = rs.getString(1);
                    if (tablets_for_symptoms.containsKey(sym_search)) {
                        tablets_for_symptoms.put(sym_search, (Integer) tablets_for_symptoms.get(sym_search) + 1);
                    } else {
                        tablets_for_symptoms.put(sym_search, 1);
                    }
                }
                System.out.println(tablets_for_symptoms);
            }
            if (!tablets_for_symptoms.isEmpty()) {
                String tablet_selected = tablets_for_symptoms.lastKey().toString();
                System.out.println(tablet_selected);
                suggested_tablets.add(tablet_selected);
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("SELECT tabletuse from WS.TABLETS where tabletname='" + tablet_selected + "'");
                String symptom_for_selected_tablet = "";
                while (rs2.next()) {
                    symptom_for_selected_tablet = rs2.getString(1);
                    if (sym_list_str_array.contains(symptom_for_selected_tablet)) {
                        System.out.println(sym_list_str_array);
                        sym_list_str_array.remove(symptom_for_selected_tablet);
                    }
                }
            } else {
                sym_list_str_array.clear();
                suggested_tablets.add("We Dont have database for all the symptoms you provided!, please contact Adminstrator or a Doctor");
            }
        }
        return suggested_tablets;
    }

    public ResultSet getAllSymptoms() throws Exception {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT DISTINCT TABLETUSE from WS.TABLETS");
        return rs;
    }

}
