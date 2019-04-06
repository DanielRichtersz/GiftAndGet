package danielrichtersz.controllers.interfaces;

import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity createUser(String email, String username, String password);

    ResponseEntity editUser(String email, String password);

    ResponseEntity deleteUser(String email);
}