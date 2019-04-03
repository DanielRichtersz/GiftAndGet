package danielrichtersz.controllers.impl;

import danielrichtersz.models.User;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UserHelpMethods {

    @Autowired
    private UserService userService;

    protected ResponseEntity createOrUpdateUser(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.createOrUpdateUser(email, username, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    protected ResponseEntity checkUserParameters(String email, String username, String password) {

        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please insert a valid username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please insert a valid password");
        }

        if (email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please insert a valid email");
        }
        return null;
    }
}
