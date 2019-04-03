package danielrichtersz.controllers.impl;

import danielrichtersz.controllers.interfaces.UserController;
import danielrichtersz.models.User;
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
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    private UserHelpMethods userHelpMethods;

    @PostMapping("/users")
    @Override
    public ResponseEntity createUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        ResponseEntity responseEntity = userHelpMethods.checkUserParameters(email, username, password);

        if (responseEntity != null) {
            return responseEntity;
        }

        return userHelpMethods.createOrUpdateUser(email, username, password);
    }

    @Override
    public ResponseEntity editUser(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {

        if (!userService.emailInUse(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with this email could be found");
        }

        return userHelpMethods.createOrUpdateUser(email, username, password);
    }

    @Override
    public ResponseEntity deleteUser(String email) {
        if (!userService.emailInUse(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with this email could be found");
        }

        if (!userService.deleteUser(email)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be deleted");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User was succesfully deleted");
    }
}
