

import LibraryAction.JavaClass;
import LibraryAll.JavaClassGetAll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JCAction extends HttpServlet {

    JavaClass jc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {

        jc = new JavaClass();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        System.out.println("asd");
        HttpSession session = request.getSession();
        if (request.getParameter("Signin") != null) {
            String data;
            String username = request.getParameter("signin_username");
            String password = request.getParameter("signin_password");

            data = jc.signin(username, password);

            if (!"failed".equals(data)) {
                session.setAttribute("name", data);
                session.setAttribute("username", username);
            } else {
                out.write(data);
            }

        } else if (request.getParameter("Signup") != null) {

            String name = request.getParameter("signup_fullname");
            String username = request.getParameter("signup_username");
            String email = request.getParameter("signup_email");
            String dob = request.getParameter("signup_dob");
            String pwd = request.getParameter("signup_Password");

            String data = jc.signup(name, username, email, dob, pwd);

            if (!"failed".equals(data)) {
                session.setAttribute("name", data);
                session.setAttribute("username", username);
            } else {
                out.write(data);
            }

        } else if (request.getParameter("GetAll") != null) {

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            String data = JavaClassGetAll.GetAllData();
            out.print(data);
            System.out.println("a: " + data);

        } else if (request.getParameter("Rent") != null) {
            System.out.println(request.getParameter("Rent"));
            if (session.getAttribute("name") != null) {
                if (request.getParameter("Rent") != null) {
                    jc.Rent(Integer.parseInt(request.getParameter("Rent")));
                    out.write("success");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(JCAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(JCAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
