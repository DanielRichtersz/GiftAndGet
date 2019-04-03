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
        return userRepository.save(new User(email, username, password));
    }
}
