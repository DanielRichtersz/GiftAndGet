package danielrichtersz.controllers.impl;

import danielrichtersz.controllers.interfaces.UserController;
import danielrichtersz.models.User;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @Override
    public ResponseEntity createUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        ResponseEntity responseEntity = checkUserParameters(email, username, password);

        if (responseEntity != null) {
            return responseEntity;
        }

        if (userService.emailInUse(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already in use");
        }

        if (userService.usernameInUse(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This username is already in use");
        }

        User user = userService.createUser(email, username, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be created");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users")
    @Override
    public ResponseEntity editUser(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password) {

        User user = userService.getByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with this email could be found");
        }

        user = userService.updateUser(email, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be updated");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping("/users")
    @Override
    public ResponseEntity deleteUser(String email) {
        if (!userService.emailInUse(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with this email could be found");
        }

        if (userService.hasCharities(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user has open charities. Please deactivate the charities first.");
        }

        if (!userService.deleteUser(email)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be deleted");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User was succesfully deleted");
    }

    protected ResponseEntity checkUserParameters(String email, String username, String password) {

        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert a valid username");
        }

        if (password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert a valid password");
        }

        if (email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please insert a valid email");
        }
        return null;
    }
}
