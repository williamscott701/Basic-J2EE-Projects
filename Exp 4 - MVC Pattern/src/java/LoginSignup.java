
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSignup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            PrintWriter out = response.getWriter();

            response.setContentType("text/html;charset=UTF-8");

            HttpSession hs = request.getSession();
            String sbutton = request.getParameter("submit_button");

            switch (sbutton) {
                case "SignIn": {
                    String username = request.getParameter("signin_username");
                    String password = request.getParameter("signin_password");
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
                    String query = "select name from WS.REGISTERED_USERS where USERNAME='" + username + "' and PASSWORD='" + password.hashCode() + "'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        hs.setAttribute("username", rs.getString("name"));
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
//                        response.sendRedirect("index.jsp");
                    } else {
                        out.println("<div class='container alert alert-danger'>Invalid Username or Password, Try Again!....</div>");
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "SignUp": {
                    String name = request.getParameter("signup_fullname");
                    String username = request.getParameter("signup_username");
                    String email = request.getParameter("signup_email");
                    String dob = request.getParameter("signup_dob");
                    String pwd = request.getParameter("signup_Password");
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/J2EE-Lab-Experiments", "ws", "6477");
                    String query = "insert into ws.Registered_users values('" + name + "', '" + username + "', '" + email + "', '" + dob + "', '" + pwd.hashCode() + "')";
                    Statement st = con.createStatement();
                    int a = st.executeUpdate(query);
                    if (a == 1) {
                        hs.setAttribute("username", name);
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
                    } else {
                        out.print("Registration Error");
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.include(request, response);
                    }
                    break;
                }
                default:
                    out.println("Invalid Request");
                    break;
            }

        } catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {
            System.out.println(ex);
        }
    }
}
