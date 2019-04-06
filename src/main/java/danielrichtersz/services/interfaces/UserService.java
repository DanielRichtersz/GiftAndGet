package danielrichtersz.services.interfaces;

import danielrichtersz.models.UserAccount;

public interface UserService {
    UserAccount createUser(String email, String username, String password);

    UserAccount updateUser(String email, String password);

    boolean emailInUse(String email);

    boolean usernameInUse(String username);

    boolean hasCharities(String email);

    boolean deleteUser(String email);

    UserAccount getByEmail(String email);

}
