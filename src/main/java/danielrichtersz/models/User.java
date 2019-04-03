package danielrichtersz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String username;
    private String password;

    public User() {

    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return this.email + "-" + this.username + "-" + this.password;
    }
}
