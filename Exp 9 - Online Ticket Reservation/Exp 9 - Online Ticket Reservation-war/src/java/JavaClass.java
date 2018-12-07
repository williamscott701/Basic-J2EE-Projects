
import com.Bankaccount;
import com.BankaccountFacadeLocal;
import com.Reservationhistory;
import com.ReservationhistoryFacadeLocal;
import com.Train;
import com.TrainFacadeLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.json.simple.JSONArray;

public class JavaClass {

    ReservationhistoryFacadeLocal reservationhistoryFacade = lookupReservationhistoryFacadeLocal();
    BankaccountFacadeLocal bankaccountFacade = lookupBankaccountFacadeLocal();
    TrainFacadeLocal trainFacade = lookupTrainFacadeLocal();

    MailService ms = new MailService();

    public String signin(String username, String password) {
        if (bankaccountFacade.find(username) == null) {
            return "failed";
        } else {
            if (bankaccountFacade.find(username).getPassword().equals(password.hashCode() + "")) {
                return bankaccountFacade.find(username).getName();
            } else {
                return "failed";
            }
        }
    }

    public String signup(String name, String username, String email, String dob, String password) {
        Bankaccount a = new Bankaccount(username, name, email, dob, password.hashCode() + "", 500);
        try {
            bankaccountFacade.create(a);
        } catch (Exception e) {
            return "failed";
        }
        return name;
    }

    public String reserveTrain(String name, String email, int age, int trainid, String username, String dateofjourney) {
        Train a = trainFacade.find(trainid);
        Bankaccount b = bankaccountFacade.find(username);
        Reservationhistory r = new Reservationhistory();
        r.setDateofbooking(new Date().toString());
        r.setDateofjourney(dateofjourney);
        r.setPassengerage(age + "");
        r.setPassengeremail(email);
        r.setPassengername(name);
        r.setTrainid(trainid + "");
        r.setUsername(username);
        r.setPaid(a.getCost());
        if (b.getBalance() >= a.getCost() && a.getAvailable() > 0) {
            try {
                b.setBalance(b.getBalance() - a.getCost());
                bankaccountFacade.edit(b);
                a.setAvailable(a.getAvailable() - 1);
                trainFacade.edit(a);
                reservationhistoryFacade.create(r);
                String subject = "Ticket Booked";
                String text = "Congratulations, your Ticket for train " + r.getTrainid() + " has been reserved sucessfully."
                        + "<br>date of journey: " + r.getDateofjourney()
                        + "<br>Passenger Name : " + r.getPassengername()
                        + "<br>Passenger Age  : " + r.getPassengerage()
                        + "<br><br>Your Ticket has been booked by: " + r.getUsername()
                        + "<br><br><br>This is an auto Generated mail, Please dont reply";
                ms.ticketReserved(email, subject, text);
                text = "Congratulations, you have booked a Ticket for train " + r.getTrainid() + " sucessfully"
                        + "<br>date of journey: " + r.getDateofjourney()
                        + "<br>Passenger Name : " + r.getPassengername()
                        + "<br>Passenger Age  : " + r.getPassengerage()
                        + "<br><br><br>This is an auto Generated mail, Please dont reply";
                ms.ticketReserved(r.getUsername(), subject, text);
                return "true";
            } catch (MessagingException ex) {
                Logger.getLogger(JavaClass.class.getName()).log(Level.SEVERE, null, ex);
                return "falsemail";
            }
        }
        return "";
    }

    public String cancelTicket(String dateofbooking) {
        try {
            Reservationhistory r = reservationhistoryFacade.find(dateofbooking);
            Train t = trainFacade.find(Integer.parseInt(r.getTrainid()));
            Bankaccount b = bankaccountFacade.find(r.getUsername());
            b.setBalance(b.getBalance() + r.getPaid());
            bankaccountFacade.edit(b);
            t.setAvailable(t.getAvailable() + 1);
            trainFacade.edit(t);
            reservationhistoryFacade.remove(r);
            String subject = "Ticket Booked";
            String text = "Your Ticket for train " + r.getTrainid() + " with the following details has been Cancelled Sucessfully."
                    + "<br>date of journey: " + r.getDateofjourney()
                    + "<br>Passenger Name : " + r.getPassengername()
                    + "<br>Passenger Age  : " + r.getPassengerage()
                    + "<br><br>Your Ticket was cancelled by: " + r.getUsername()
                    + "<br><br><br>This is an auto Generated mail, Please dont reply";
            ms.ticketReserved(r.getPassengername(), subject, text);
            text = "You have Cancelled a Ticket for train " + r.getTrainid() + " Sucessfully"
                    + "<br>date of journey: " + r.getDateofjourney()
                    + "<br>Passenger Name : " + r.getPassengername()
                    + "<br>Passenger Age  : " + r.getPassengerage()
                    + "<br><br><br>This is an auto Generated mail, Please dont reply";
            ms.ticketReserved(r.getUsername(), subject, text);
            return "true";
        } catch (MessagingException ex) {
            Logger.getLogger(JavaClass.class.getName()).log(Level.SEVERE, null, ex);
            return "falsemail";
        }
    }

    public void addTrain(int id, String name, int available, int cost) {
        if (trainFacade.find(id) != null) {
            Train b = trainFacade.find(id);
            b.setName(name);
            b.setAvailable(available);
            b.setCost(cost);
            trainFacade.edit(b);
        } else {
            Train b = new Train();
            b.setName(name);
            b.setId(id);
            b.setAvailable(available);
            b.setCost(cost);
            trainFacade.create(b);
        }
    }

    public JSONArray getAllData() throws ClassNotFoundException, SQLException, Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
        String query = "select * from train where available>0";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return WSConvert.convertResultSetIntoJSON(rs);
    }

    public JSONArray getHistoryData(String username) throws ClassNotFoundException, SQLException, Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/j2eeexp", "ws", "6477");
        String query = "select * from reservationhistory where username='" + username + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return WSConvert.convertResultSetIntoJSON(rs);
    }

    public String Balance(String username) {
        if (bankaccountFacade.find(username) == null) {
            return "failed";
        }
        return bankaccountFacade.find(username).getBalance() + "";
    }

    public String transfer(String username, String transfername, int amount) {
        if (bankaccountFacade.find(transfername) != null) {
            Bankaccount a = bankaccountFacade.find(username);
            if (a.getBalance() >= amount) {
                a.setBalance(a.getBalance() - amount);
                bankaccountFacade.edit(a);
                a = bankaccountFacade.find(transfername);
                a.setBalance(a.getBalance() + amount);
                bankaccountFacade.edit(a);
                return "true";
            } else {
                return "false";
            }
        }
        return "false";
    }

    public void deposit(String username, int amount) {
        Bankaccount a = bankaccountFacade.find(username);
        a.setBalance(a.getBalance() + amount);
        bankaccountFacade.edit(a);
    }

    public String withdraw(String username, int amount) {
        Bankaccount a = bankaccountFacade.find(username);
        if (a.getBalance() >= amount) {
            a.setBalance(a.getBalance() - amount);
            bankaccountFacade.edit(a);
            return "true";
        }
        return "false";
    }

    private TrainFacadeLocal lookupTrainFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (TrainFacadeLocal) c.lookup("java:global/Exp_9_-_Online_Ticket_Reservation/Exp_9_-_Online_Ticket_Reservation-ejb/TrainFacade!com.TrainFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private BankaccountFacadeLocal lookupBankaccountFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BankaccountFacadeLocal) c.lookup("java:global/Exp_9_-_Online_Ticket_Reservation/Exp_9_-_Online_Ticket_Reservation-ejb/BankaccountFacade!com.BankaccountFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ReservationhistoryFacadeLocal lookupReservationhistoryFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ReservationhistoryFacadeLocal) c.lookup("java:global/Exp_9_-_Online_Ticket_Reservation/Exp_9_-_Online_Ticket_Reservation-ejb/ReservationhistoryFacade!com.ReservationhistoryFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
