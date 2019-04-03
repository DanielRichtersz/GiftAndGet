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
    public User createOrUpdateUser(String email, String username, String password) {
        return userRepository.save(new User(email, username, password));
    }

    @Override
    public boolean emailInUse(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        userRepository.delete(user);
        user = userRepository.findByEmail(email);
        return user == null;
    }
}
