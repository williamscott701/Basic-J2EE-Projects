
package LibraryAction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RegisteredUsersFacade extends AbstractFacade<RegisteredUsers> implements RegisteredUsersFacadeLocal {
    @PersistenceContext(unitName = "Exp_8_-_Library_Management_using_JMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegisteredUsersFacade() {
        super(RegisteredUsers.class);
    }
}
