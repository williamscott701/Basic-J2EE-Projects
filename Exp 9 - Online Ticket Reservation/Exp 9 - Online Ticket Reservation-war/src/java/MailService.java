
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public void ticketReserved(String toemail, String subject, String text) throws MessagingException {

        String host = "smtp.gmail.com";
        String from = "unauthorisedmotiondetected@gmail.com";
        String pass = "piyushprakash";
        String port = "465";
        String to = toemail;
        String socket = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.class", socket);

        Session session = Session.getInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setText(text);
        message.setSubject(subject);
        message.addRecipient(RecipientType.TO, new InternetAddress(to));

        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
    }

}
