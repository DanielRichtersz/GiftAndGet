package danielrichtersz.controllers.impl;

import danielrichtersz.controllers.interfaces.ActionGroupController;
import danielrichtersz.models.ActionGroup;
import danielrichtersz.models.Charity;
import danielrichtersz.models.UserAccount;
import danielrichtersz.services.interfaces.ActionGroupService;
import danielrichtersz.services.interfaces.CharityService;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class ActionGroupControllerImpl implements ActionGroupController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActionGroupService actionGroupService;

    @PostMapping("/actiongroups")
    @Override
    public ResponseEntity createActionGroup(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("minimum") Long minimumDonation,
            @RequestParam("username") String username,
            @RequestParam("charityName") String charityName) {

        Charity charity = charityService.getCharityByCharityName(charityName);

        if (charity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The given charity could not be found.");
        }

        UserAccount userAccount = userService.getByUsername(username);

        if (userAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No useraccount bound to this email could be found");
        }

        ActionGroup actionGroup = actionGroupService.createNewActionGroup(title, description, minimumDonation, userAccount, charity);

        if (actionGroup == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Actiongroup could not be created, please try again");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(actionGroup);
    }
}
