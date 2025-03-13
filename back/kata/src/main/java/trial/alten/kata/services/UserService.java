package trial.alten.kata.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trial.alten.kata.models.Role;
import trial.alten.kata.models.User;
import trial.alten.kata.repositories.RoleRepository;
import trial.alten.kata.repositories.UserRepository;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if ("admin@admin.com".equals(user.getEmail())) {
            Role adminRole = roleRepository.findByName("ADMIN");
            user.setRole(adminRole);
        }else {
            Role userRole = roleRepository.findByName("USER");
            user.setRole(userRole);
        }

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
