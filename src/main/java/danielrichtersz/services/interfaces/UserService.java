package danielrichtersz.services.interfaces;

import danielrichtersz.models.User;

public interface UserService {

    User createUser(String email, String username, String password);
}
