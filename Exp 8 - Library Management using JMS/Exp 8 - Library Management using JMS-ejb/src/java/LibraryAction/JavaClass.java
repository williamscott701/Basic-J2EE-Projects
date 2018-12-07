package LibraryAction;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JavaClass {
    
    BooksFacadeLocal booksFacade = lookupBooksFacadeLocal();
    
    RegisteredUsersFacadeLocal registeredUsersFacade = lookupRegisteredUsersFacadeLocal();
    
    public String signin(String username, String password) {
        if (registeredUsersFacade.find(username) == null) {
            return "failed";
        } else {
            if (registeredUsersFacade.find(username).getPwd().equals(password)) {
                return registeredUsersFacade.find(username).getName();
            } else {
                return "failed";
            }
        }
    }
    
    public String signup(String name, String username, String email, String dob, String password) {
        RegisteredUsers a = new RegisteredUsers();
        a.setName(name);
        a.setUsername(username);
        a.setEmail(email);
        a.setDob(dob);
        a.setPwd(password);
        try {
            registeredUsersFacade.create(a);
        } catch (Exception e) {
            return "failed";
        }
        return name;
    }
    
    public void Rent(int bookid) {
        Books a = booksFacade.find(bookid);
        System.out.println(a.getCount());
        a.setCount(a.getCount() - 1);
        booksFacade.edit(a);
    }
    
    private RegisteredUsersFacadeLocal lookupRegisteredUsersFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RegisteredUsersFacadeLocal) c.lookup("java:global/Exp_8_-_Library_Management_using_JMS/Exp_8_-_Library_Management_using_JMS-ejb/RegisteredUsersFacade!LibraryAction.RegisteredUsersFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private BooksFacadeLocal lookupBooksFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BooksFacadeLocal) c.lookup("java:global/Exp_8_-_Library_Management_using_JMS/Exp_8_-_Library_Management_using_JMS-ejb/BooksFacade!LibraryAction.BooksFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
