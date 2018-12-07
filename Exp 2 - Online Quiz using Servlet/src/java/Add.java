import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String category = request.getParameter("category");
            String question = request.getParameter("question");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            String answer = request.getParameter("answer");

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
            String query = "insert into WS.QUIZ values('" + category + "', '" + question + "', '" + option1 + "', '" + option2 + "', '" + option3 + "', '" + option4 + "', '" + answer + "')";
            Statement st = con.createStatement();
            int a = st.executeUpdate(query);
            RequestDispatcher rd;
            if (a == 1) {
                out.print("<script>alert('Your Question Added Sucessfully');</script>");
                rd = request.getRequestDispatcher("Quiz");
                rd.include(request, response);
            } else {
                out.print("Error");
                rd = request.getRequestDispatcher("Quiz");
                rd.include(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
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
