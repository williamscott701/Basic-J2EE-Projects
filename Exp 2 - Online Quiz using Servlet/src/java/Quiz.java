
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

public class Quiz extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
            String query = "select distinct category  from WS.QUIZ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            HttpSession hs = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/quizstyle.html");
            rd.include(request, response);
            out.println("<body style='overflow-x:hidden;'>"
                    + "<p style='position:relative;left:70%;font-size:1.3em; color:white; display:inline;'>Welcome " + hs.getAttribute("username") + "<form action=index.html method=post style='display:inline;'><input  class='btn btn-default' type=submit value=Logout></form></p>"
                    + "<div style='width:600px; margin:0 auto;'>"
                    + "<form action=Play method=post class='form' style='margin-top:30px;'>"
                    + "<p style='font-size:1.4em;margin:0 auto;padding:10px'>Play Quiz</p>"
                    + "<select name=category class='form-control'>");
            while (rs.next()) {
                out.println("<option class='form-control'>"
                        + rs.getString("category")
                        + "</option>");
            }
            out.println("</select>"
                    + "<input type=submit class='btn btn-default'  name=submit_button value='Play Quiz'>"
                    + "</form>"
                    + "<form action=Add method=post class='form-horizantal'>"
                    + "<p style='font-size:1.4em;margin:0 auto;padding:10px'>Add a Question to the Quiz</p>"
                    + "<input class='form-control' type=text name=category placeholder='Enter Category'>"
                    + "<input class='form-control'  type=text name=question placeholder='Enter Question'>"
                    + "<input class='form-control'  type=text name=option1 placeholder='Enter Option1'>"
                    + "<input class='form-control'  type=text name=option2 placeholder='Enter Option2'>"
                    + "<input class='form-control'  type=text name=option3 placeholder='Enter Option3'>"
                    + "<input class='form-control'  type=text name=option4 placeholder='Enter Option4'>"
                    + "<input class='form-control'  type=text name=answer placeholder='Enter Answer'>"
                    + "<input type=submit  class='btn btn-default col-sm-offset-5'  value='Add Question'>"
                    + "</form>"
                    + "</div>");
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
