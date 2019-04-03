package danielrichtersz.services.interfaces;

import danielrichtersz.models.User;

public interface UserService {
    User createUser(String email, String username, String password);

    User updateUser(String email, String password);

    boolean emailInUse(String email);

    boolean usernameInUse(String username);

    boolean hasCharities(String email);

    boolean deleteUser(String email);

    User getByEmail(String email);

}
