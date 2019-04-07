package danielrichtersz.services.impl;

import danielrichtersz.models.ActionGroup;
import danielrichtersz.models.Charity;
import danielrichtersz.models.UserAccount;
import danielrichtersz.repositories.ActionGroupRepository;
import danielrichtersz.services.interfaces.ActionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionGroupServiceImpl implements ActionGroupService {

    @Autowired
    private ActionGroupRepository actionGroupRepository;

    @Override
    public ActionGroup createNewActionGroup(String title, String description, Long minimumDonation, UserAccount userAccount, Charity charity) {
        return actionGroupRepository.save(new ActionGroup(title, description, minimumDonation, userAccount, charity));
    }
}
