
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JMSAction", urlPatterns = {"/JMSAction"})

public class JMSAction extends HttpServlet {

    @Resource(mappedName = "booksQueue")
    private Queue booksQueue;
    @Resource(mappedName = "booksConnectionFactory")
    private QueueConnectionFactory context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            if (!request.getParameter("name").equals("") && !request.getParameter("id").equals("") && !request.getParameter("count").equals("")) {

                HashMap<String, String> hm = new HashMap();
                Integer.parseInt(request.getParameter("id"));
                Integer.parseInt(request.getParameter("count"));
                hm.put("name", request.getParameter("name"));
                hm.put("id", request.getParameter("id"));
                hm.put("count", request.getParameter("count"));

                sendJMSMessageToBooksQueue(hm);

                response.getWriter().write("success");
            } else {
                response.getWriter().write("failed");
            }
        } catch (Exception e) {
            response.getWriter().write("failed");
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

    private void sendJMSMessageToBooksQueue(HashMap<String, String> d) {
        try {
            System.out.println(d);
            JMSContext ct = context.createContext();
            ObjectMessage om = ct.createObjectMessage();
            om.setObject((Serializable) d);
            ct.createProducer().send(booksQueue, om);
        } catch (JMSException ex) {
            Logger.getLogger(JMSAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
