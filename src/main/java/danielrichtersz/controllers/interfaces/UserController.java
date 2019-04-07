package danielrichtersz.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {

    ResponseEntity createUser(String email, String username, String password);

    ResponseEntity editUser(String email, String password);

    ResponseEntity loginUser(String email,
                             String password);

    ResponseEntity getUser(String username);

    ResponseEntity deleteUser(String email);
}