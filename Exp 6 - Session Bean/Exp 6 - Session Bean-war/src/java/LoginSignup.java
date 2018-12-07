
import a.PayrollBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSignup extends HttpServlet {

    @EJB
    private PayrollBeanLocal payrollBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html;charset=UTF-8");

            HttpSession session = request.getSession();
            String sbutton = request.getParameter("submit_button");

            switch (sbutton) {
                case "SignIn": {
                    String username = request.getParameter("signin_username");
                    String password = request.getParameter("signin_password");
                    String name = payrollBean.signin(username, password);
                    session.setAttribute("name", name);
                    session.setAttribute("username", username);
                    if (!"failed".equals(name)) {
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
                    } else {
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
                    String return_value = payrollBean.signup(name, username, email, dob, pwd);
                    session.setAttribute("name", return_value);
                    session.setAttribute("username", username);
                    if (!"failed".equals(return_value)) {
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.include(request, response);
                    }
                    break;
                }
                default:
                    session.setAttribute("name", "failed");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                    break;
            }

        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }
}
