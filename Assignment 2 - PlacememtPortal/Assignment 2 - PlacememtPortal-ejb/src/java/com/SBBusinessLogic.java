package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.json.simple.JSONArray;

@Stateless
public class SBBusinessLogic implements SBBusinessLogicLocal {

    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private TesthistoryFacadeLocal testhistoryFacade;
    @EJB
    private QuestionsFacadeLocal questionsFacade;

    public String comp;

    @Override
    public String signIn(String username, String password) {
        if (usersFacade.find(username) == null) {
            return "false";
        } else {
            Users r = usersFacade.find(username);
            if (r.getPassword().equals(password.hashCode() + "")) {
                return r.getName();
            } else {
                return "false";
            }
        }
    }

    @Override
    public String signUp(String name, String username, String email, String dob, String password) {
        try {
            Users u = new Users(username, name, email, dob, password.hashCode() + "");
            u.setScore(10);
            usersFacade.create(u);
        } catch (Exception e) {
            return "false";
        }
        return name;
    }

    @Override
    public String addQuestion(String question, String option1, String option2, String option3, String option4, String answer, String company) {
        try {
            Questions q = new Questions(new Date().toString(), question, option1, option2, option3, option4, answer, company);
            questionsFacade.create(q);
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

    @Override
    public String getScore(String username) {
        try {
            return usersFacade.find(username).getScore() + "";
        } catch (Exception e) {
            return "false";
        }
    }

    @Override
    public JSONArray getTop(int n) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
            String query = "SELECT \"NAME\", EMAIL, SCORE FROM WS.USERS ORDER BY SCORE DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return WSConvert.convertResultSetIntoJSON(rs, n);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONArray getHistory(String username) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
            String query = "SELECT \"CREATEDON\", COMPANYNAME, SCORE FROM WS.TESTHISTORY  where username = '" + username + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return WSConvert.convertResultSetIntoJSON(rs);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONArray getCompany() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
            String query = "select distinct COMPANY from WS.QUESTIONS";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return WSConvert.convertResultSetIntoJSON(rs);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONArray getQuestions(String company, int count) {
        try {
            comp = company;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
            String query = "SELECT * FROM QUESTIONS WHERE COMPANY='" + company + "' ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT " + count + " ROW ONLY";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return WSConvert.convertResultSetIntoJSON(rs);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String submitTest(Map<String, String[]> parameterMap, int n, String username) {
        try {
            int count = 0;
            for (String key : parameterMap.keySet()) {
                String value = ((String[]) parameterMap.get(key))[0];
                if (!key.equals("SubmitTest")) {
                    if (questionsFacade.find(key).getAnswer().equals(value)) {
                        count++;
                    }
                }
            }
            int percent = count * 100 / n;
            testhistoryFacade.create(new Testhistory(new Date().toString(), username, comp, percent));
            Users u = usersFacade.find(username);
            u.setScore(u.getScore() + percent);
            usersFacade.edit(u);
            return "true";
        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }
    }

}
