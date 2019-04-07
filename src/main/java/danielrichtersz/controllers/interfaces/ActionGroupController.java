package danielrichtersz.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

public interface ActionGroupController {

    ResponseEntity createActionGroup(String title, String description, Long minimumDonation, String username, String charityName);

}
