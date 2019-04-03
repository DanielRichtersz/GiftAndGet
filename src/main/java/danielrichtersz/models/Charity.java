package danielrichtersz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Charity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User owner;

    private String name;
    private String email;
    private String phonenumber;
    private String description;
    private String bankAccount;

    private boolean verifiedCharity;

    public Charity() {

    }

    public Charity(User owner, String name, String email, String phonenumber, String description, String bankAccount) {
        this.owner = owner;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.description = description;
        this.bankAccount = bankAccount;
        this.verifiedCharity = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean getVerifiedCharity() {
        return this.verifiedCharity;
    }

    public void setVerifiedCharity(boolean verified) {
        this.verifiedCharity = verified;
    }
}
