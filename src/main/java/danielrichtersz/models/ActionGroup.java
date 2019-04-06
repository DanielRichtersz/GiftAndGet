package danielrichtersz.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ActionGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    private Long minimumAmount;

    private boolean isClosed;

    @ManyToOne
    private UserAccount owner;

    @ManyToOne
    private Charity charity;

    @OneToMany
    private List<UserAccount> userAccounts;

    public ActionGroup() {

    }

    public ActionGroup(String title, String description, Long minimumDonation, UserAccount owner, Charity charity) {
        this.title = title;
        this.description = description;
        this.minimumAmount = minimumDonation;
        this.owner = owner;
        this.charity = charity;
        this.userAccounts = new ArrayList<>();
        this.isClosed = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinimumAmount() {
        return this.minimumAmount;
    }

    public boolean getIsClosed() {
        return this.isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
}
