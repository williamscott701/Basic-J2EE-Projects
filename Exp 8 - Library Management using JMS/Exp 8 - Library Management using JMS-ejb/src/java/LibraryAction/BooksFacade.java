
package LibraryAction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BooksFacade extends AbstractFacade<Books> implements BooksFacadeLocal {
    @PersistenceContext(unitName = "Exp_8_-_Library_Management_using_JMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BooksFacade() {
        super(Books.class);
    }
    
}
