
import a.PayrollBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Action extends HttpServlet {

    @EJB
    private PayrollBeanLocal payrollBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("payroll") != null) {
                session.setAttribute("payrollMap", payrollBean.getPayroll((String) session.getAttribute("username")));
                RequestDispatcher rd = request.getRequestDispatcher("payroll.jsp");
                rd.forward(request, response);
            }

            if (request.getParameter("bp") != null) {
                String username = request.getParameter("username");
                double bp = Double.parseDouble(request.getParameter("bp"));
                double hra = Double.parseDouble(request.getParameter("hra"));
                double tax = Double.parseDouble(request.getParameter("tax"));
                boolean flag = payrollBean.setPayDetails(username, bp, hra, tax);
                request.setAttribute("flag", flag);
                RequestDispatcher rd = request.getRequestDispatcher("admin_modify.jsp");
                rd.forward(request, response);
            }
        } catch (ServletException | IOException e) {

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
