package danielrichtersz.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<ActionGroup> actionGroups;

    private boolean verifiedCompany;

    public User() {

    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.actionGroups = new ArrayList<>();
        this.verifiedCompany = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean checkPassword(String password) {
        return (this.password.equals(password));
    }

    public void setPassword(String newPassword) {
        this.password = password;
    }

    public boolean isVerifiedCompany() {
        return this.verifiedCompany;
    }

    public void setVerifiedCompany(boolean verifiedCompany) {
        this.verifiedCompany = verifiedCompany;
    }

    @Override
    public String toString() {
        return this.email + "-" + this.username + "-" + this.password;
    }
}
