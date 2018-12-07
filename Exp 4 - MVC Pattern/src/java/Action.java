
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Action extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Search_Controller s = new Search_Controller();
            HttpSession session = request.getSession();

            if (request.getParameter("sym") != null) {
                String sym = request.getParameter("sym");
                HashSet suggested_tablets = s.suggestTablets(sym);
                request.setAttribute("suggested_tablets", suggested_tablets);
                request.getRequestDispatcher("results.jsp").forward(request, response);
            }
            if (request.getParameter("getall") != null) {
                session.setAttribute("symlist", s.getAllSymptoms());
            }
        } catch (Exception ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
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
