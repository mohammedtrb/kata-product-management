package trial.alten.kata.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trial.alten.kata.models.User;
import trial.alten.kata.models.security.UserDetailsImpl;
import trial.alten.kata.repositories.UserRepository;
import trial.alten.kata.services.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Mot de passe invalide");
        }
        return jwtService.generateToken(new UserDetailsImpl(user).getUsername());
    }
}
