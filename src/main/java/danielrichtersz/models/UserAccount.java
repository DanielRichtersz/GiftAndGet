package danielrichtersz.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "owner")
    private List<ActionGroup> actionGroups;

    @OneToMany(mappedBy = "owner")
    private List<Charity> charities;

    private boolean verifiedCompany;

    public UserAccount() {

    }

    public UserAccount(String email, String username, String password) {
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

    public List<Charity> getCharities() {
        return this.charities;
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
