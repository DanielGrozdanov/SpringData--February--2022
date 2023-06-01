package example.services;

import example.models.User;
import example.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        User found = this.userRepository.findByUsername(user.getUsername());
        if (found == null) {
            userRepository.save(user);
        }
    }
}
