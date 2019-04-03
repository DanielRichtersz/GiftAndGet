package danielrichtersz.services.impl;

import danielrichtersz.models.User;
import danielrichtersz.repositories.UserRepository;
import danielrichtersz.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String email, String username, String password) {
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
            return null;
        }

        return userRepository.save(new User(email, username, password));
    }

    @Override
    public User updateUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        userRepository.delete(user);
        user = userRepository.findByEmail(email);
        return user == null;
    }

    @Override
    public User getByEmail(String email) {
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
}
