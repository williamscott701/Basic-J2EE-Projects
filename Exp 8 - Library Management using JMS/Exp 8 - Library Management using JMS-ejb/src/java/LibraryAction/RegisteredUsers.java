
package LibraryAction;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "REGISTERED_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisteredUsers.findAll", query = "SELECT r FROM RegisteredUsers r"),
    @NamedQuery(name = "RegisteredUsers.findByName", query = "SELECT r FROM RegisteredUsers r WHERE r.name = :name"),
    @NamedQuery(name = "RegisteredUsers.findByUsername", query = "SELECT r FROM RegisteredUsers r WHERE r.username = :username"),
    @NamedQuery(name = "RegisteredUsers.findByEmail", query = "SELECT r FROM RegisteredUsers r WHERE r.email = :email"),
    @NamedQuery(name = "RegisteredUsers.findByDob", query = "SELECT r FROM RegisteredUsers r WHERE r.dob = :dob"),
    @NamedQuery(name = "RegisteredUsers.findByPwd", query = "SELECT r FROM RegisteredUsers r WHERE r.pwd = :pwd")})
public class RegisteredUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 70)
    @Column(name = "NAME")
    private String name;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "USERNAME")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 30)
    @Column(name = "DOB")
    private String dob;
    @Size(max = 300)
    @Column(name = "PWD")
    private String pwd;

    public RegisteredUsers() {
    }

    public RegisteredUsers(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisteredUsers)) {
            return false;
        }
        RegisteredUsers other = (RegisteredUsers) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LibraryAction.RegisteredUsers[ username=" + username + " ]";
    }    
}
