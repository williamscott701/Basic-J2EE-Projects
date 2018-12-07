import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Play extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession hs = request.getSession();

            int score = 0, count = 1;
            String category, choice = "", ans = "";
            if (request.getParameter("submitanswer") != null) {
                String submit_button = request.getParameter("submitanswer");
                if (submit_button.equals("Next Question")) {
                    score = (int) hs.getAttribute("score");
                    count = (int) hs.getAttribute("count");
                    choice = request.getParameter("choice");
                    ans = hs.getAttribute("ans").toString();
                    if (ans == null ? choice == null : ans.equals(choice)) {
                        score++;
                    }
                    count++;
                }
            }

            if (request.getParameter("category") != null) {
                category = request.getParameter("category");
            } else {
                category = hs.getAttribute("category").toString();
            }

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
            String query = "select * from WS.QUIZ where category='" + category + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            RequestDispatcher rd = request.getRequestDispatcher("/quizstyle.html");
            rd.include(request, response);

            out.println("<body style='overflow-x:hidden;'>"
                    + "<p style='position:relative;left:70%;font-size:1.3em; color:white; display:inline;'>Welcome " + hs.getAttribute("username") + "<form action=index.html method=post style='display:inline;'><input  class='btn btn-default' type=submit value=Logout></form></p>"
                    + "Quesiton Count    : " + count + "<br>Current Score    : " + score + "<br>Previous Choice    : " + choice + "<br>Previous Answer    : " + ans
                    + "<br>Correct Percentage: " + (score * 100 / count) + "%"
                    + "<br><form action=Play?category=" + category + " method=post>");
            for (int i = 0; i <= count; i++) {
                rs.next();
            }
            out.println("<div id='df'><p style='padding:10px 0px;font-size:1.3em; margin:0 auto;'>Question:</p>"
                    + "<p style='margin-left:20px;'>" + rs.getString(2) + "</p>"
                    + "<p style='padding:10px 0px;font-size:1.3em; margin:0 auto;'>Options:</p>" + ""
                    + "<table style='margin-left:20px;'>"
                    + "<tr>"
                    + "<td>1)&nbsp;&nbsp;" + rs.getString(3)
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>2)&nbsp;&nbsp;" + rs.getString(4)
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>3)&nbsp;&nbsp;" + rs.getString(5)
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>4)&nbsp;&nbsp;" + rs.getString(6)
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "<br><input type=text class='form-control' placeholder='Enter Your Option' name=choice style='margin-left:30px;'>"
                    + "<input type=submit class='form-control col-sm-offset-6' style='width:auto;' name=submitanswer value='Next Question'>"
                    + "</form>"
                    + "</div>");
            hs.setAttribute("score", score);
            hs.setAttribute("count", count);
            hs.setAttribute("category", category);
            hs.setAttribute("ans", rs.getString(7));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
