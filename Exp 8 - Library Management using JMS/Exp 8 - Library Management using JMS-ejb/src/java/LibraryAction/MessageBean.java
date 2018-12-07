package LibraryAction;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "booksQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {

    @EJB
    private BooksFacadeLocal booksFacade;

    public MessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage tm = (ObjectMessage) message;
                HashMap<String, String> a = (HashMap<String, String>) tm.getObject();
                System.out.println("hm: " + a);
                String name = a.get("name");
                int id = Integer.parseInt(a.get("id"));
                int count = Integer.parseInt(a.get("count"));
                if (booksFacade.find(id) != null) {
                    Books b = booksFacade.find(id);
                    b.setName(name);
                    b.setCount(count + b.getCount());
                    booksFacade.edit(b);
                } else {
                    Books b = new Books();
                    b.setName(name);
                    b.setId(id);
                    b.setCount(count);
                    booksFacade.create(b);
                }
            } catch (JMSException ex) {
                Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
