package danielrichtersz.services.impl;

import danielrichtersz.models.UserAccount;
import danielrichtersz.repositories.UserRepository;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAccount createUser(String email, String username, String password) {
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
            return null;
        }

        return userRepository.save(new UserAccount(email, username, password));
    }

    @Override
    public UserAccount updateUser(String email, String password) {
        UserAccount userAccount = userRepository.findByEmail(email);

        if (userAccount == null) {
            return null;
        }

        userAccount.setPassword(password);
        return userRepository.save(userAccount);
    }

    @Override
    public boolean deleteUser(String email) {
        UserAccount userAccount = userRepository.findByEmail(email);

        if (userAccount.getCharities().size() > 0) {
            return false;
        }

        userRepository.delete(userAccount);
        userAccount = userRepository.findByEmail(email);
        return userAccount == null;
    }

    @Override
    public UserAccount getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean emailInUse(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean usernameInUse(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasCharities(String email) {
        UserAccount userAccount = userRepository.findByEmail(email);

        if (userAccount.getCharities().size() > 0) {
            return true;
        }

        return false;
    }
}
