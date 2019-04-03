package danielrichtersz.controllers.interfaces;

import org.springframework.http.ResponseEntity;

public interface CharityController {

    ResponseEntity submitCharityVerificationRequest(String userEmail, String name, String email, String phonenumber, String description, String bankAccount);

    ResponseEntity deleteCharity(String charityName);

    ResponseEntity verifyCharity(String charityName, boolean verified);
}
