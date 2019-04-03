package danielrichtersz.services.interfaces;

import danielrichtersz.models.User;

public interface UserService {

    User createOrUpdateUser(String email, String username, String password);
    boolean emailInUse(String email);

    boolean deleteUser(String email);
}
