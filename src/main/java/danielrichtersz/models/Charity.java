package danielrichtersz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Charity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String description;
    private String bankAccount;

    private boolean verifiedCharity;

    public Charity() {

    }

    public Charity(String name, String email, String description, String bankAccount) {
        this.name = name;
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
