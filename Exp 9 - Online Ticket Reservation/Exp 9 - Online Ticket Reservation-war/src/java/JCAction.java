
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JCAction extends HttpServlet {

    JavaClass jc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {

        jc = new JavaClass();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
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
        }

        if (request.getParameter("Signup") != null) {
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
        }

        if (request.getParameter("GetAll") != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            JSONArray data = jc.getAllData();
            out.print(data);
            System.out.println("GetAll: " + data);
        }

        if (request.getParameter("GetHistory") != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            JSONArray data = jc.getHistoryData((String) session.getAttribute("username"));
            out.print(data);
            System.out.println("getHistory: " + data);
        }

        if (request.getParameter("reserveTrain") != null) {
            String flag = "false";
            JSONObject json = WSConvert.readerToJSON(request.getReader());
            System.out.println("Trians: " + json);
            if (json != null && session.getAttribute("name") != null) {
                try {
                    flag = jc.reserveTrain((String) json.get("passengername"), (String) json.get("passengeremail"), Integer.parseInt((String) json.get("passengerage")), Integer.parseInt((String) json.get("trainid")), (String) session.getAttribute("username"), (String) json.get("dateofjourney"));
                    out.write(flag);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        if (request.getParameter("cancelTrain") != null) {
            JSONObject json = WSConvert.readerToJSON(request.getReader());
            System.out.println("Cancel: " + json);
            if (json != null && session.getAttribute("name") != null) {
                String flag = "false";
                try {
                    flag = jc.cancelTicket((String) json.get("dateofbooking"));
                    out.write(flag);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        if (request.getParameter("addTrain") != null) {
            if (session.getAttribute("name") != null) {
                jc.addTrain(Integer.parseInt(request.getParameter("id")), (String) request.getParameter("name"), Integer.parseInt(request.getParameter("available")), Integer.parseInt(request.getParameter("cost")));
                out.write("success");
            }
        }

        if (request.getParameter("Balance") != null) {
            String bal = jc.Balance((String) session.getAttribute("username")) + "";
            out.write(bal);
        }

        if (request.getParameter("Transfer") != null) {
            String flag = "false";
            if (request.getParameter("amount").length() >= 1) {
                try {
                    flag = jc.transfer((String) session.getAttribute("username"), request.getParameter("transferusername"), Integer.parseInt(request.getParameter("amount")));
                } catch (Exception e) {
                    out.write(flag);
                }
            }
            if ("true".equals(flag)) {
                String bal = jc.Balance((String) session.getAttribute("username")) + "";
                out.write(bal);
            } else {
                out.write(flag);
            }
        }

        if (request.getParameter("Deposit") != null) {
            jc.deposit((String) session.getAttribute("username"), Integer.parseInt(request.getParameter("depositamount")));
            String bal = jc.Balance((String) session.getAttribute("username")) + "";
            out.write(bal);
        }

        if (request.getParameter("Withdraw") != null) {
            String flag = jc.withdraw((String) session.getAttribute("username"), Integer.parseInt(request.getParameter("withdrawamount")));
            if ("true".equals(flag)) {
                String bal = jc.Balance((String) session.getAttribute("username")) + "";
                out.write(bal);
            } else {
                out.write(flag);
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
