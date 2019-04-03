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

    @ManyToOne
    private User owner;

    @ManyToOne
    private Charity charity;

    @OneToMany
    private List<User> users;

    public ActionGroup() {

    }

    public ActionGroup(String title, String description, Long minimumDonation, User owner, Charity charity) {
        this.title = title;
        this.description = description;
        this.minimumAmount = minimumDonation;
        this.owner = owner;
        this.charity = charity;
        this.users = new ArrayList<>();
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
}
