package danielrichtersz.services.interfaces;

import danielrichtersz.models.ActionGroup;
import danielrichtersz.models.Charity;
import danielrichtersz.models.UserAccount;

import java.util.List;

public interface ActionGroupService {

    ActionGroup createNewActionGroup(String title, String description, Long minimumDonation, UserAccount userAccount, Charity charity);

    List<ActionGroup> getAvailableActionGroups();
}
