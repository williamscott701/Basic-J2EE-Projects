
package LibraryAction;

import java.util.List;
import javax.ejb.Local;

@Local
public interface RegisteredUsersFacadeLocal {

    void create(RegisteredUsers registeredUsers);

    void edit(RegisteredUsers registeredUsers);

    void remove(RegisteredUsers registeredUsers);

    RegisteredUsers find(Object id);

    List<RegisteredUsers> findAll();

    List<RegisteredUsers> findRange(int[] range);

    int count();
}
