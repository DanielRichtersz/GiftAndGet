package danielrichtersz.controllers.impl;

import danielrichtersz.controllers.interfaces.CharityController;
import danielrichtersz.models.Charity;
import danielrichtersz.services.interfaces.CharityService;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class CharityControllerImpl implements CharityController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private UserService userService;

    @PostMapping("/charities")
    @Override
    public ResponseEntity submitCharityVerificationRequest(
            @RequestParam(value = "userEmail") String userEmail,
            @RequestParam(value = "charityName") String name,
            @RequestParam(value = "charityEmail") String email,
            @RequestParam(value = "charityPhonenumber") String phonenumber,
            @RequestParam(value = "charityDescription") String description,
            @RequestParam(value = "charityBankAccount") String bankAccount) {

        if (userEmail.isEmpty() || name.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || description.isEmpty() || bankAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not all required information has been provided. Please pass along all required information");
        }

        if (!userService.emailInUse(userEmail)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User could not be found");
        }

        if (charityService.charityNameInUse(name)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A charity with the given name already exists. "
                    + "Please check if your charity organisation has already been registered, or try a different name. "
                    + "If you suspect abuse of your company name, please contact customer support.");
        }

        Charity charity = charityService.submitCharityValidationRequest(userEmail, name, email, phonenumber, description, bankAccount);

        if (charity == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Charity validation request could not be created. Please try again or contact customer support");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(charity);
    }

    @DeleteMapping("/charities")
    @Override
    public ResponseEntity deleteCharity(@RequestParam(value = "charityName") String charityName) {
        if (charityName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid charity name provided");
        }

        if (!charityService.canBeDeleted(charityName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This charity cannot be deleted. Please contact customer support.");
        }

        if (!charityService.deleteCharity(charityName)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Charity could not be deleted");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Charity verification request has been deleted");
    }

    @PutMapping("/charities/validate")
    @Override
    public ResponseEntity verifyCharity(
            @RequestParam(value = "charityName") String charityName,
            @RequestParam(value = "verified") boolean verified) {

        if (charityName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid charity name provided");
        }

        if (!charityService.charityNameInUse(charityName)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Charity could not be found");
        }

        Charity charity = charityService.validateCharity(charityName, verified);

        if (charity == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Charity could not be verified");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(charity);

    }
}
