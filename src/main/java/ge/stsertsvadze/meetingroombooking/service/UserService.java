package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.repository.UserRepository;
import ge.stsertsvadze.meetingroombooking.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<User> getUserByCredentials(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            if (passwordEncoder.matches(password, foundUser.get().getPassword())) {
                try {
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, password)
                    );
                    String jwt = jwtUtils.generateToken(foundUser.get());
                    foundUser.get().setJwt(jwt);
                } catch (Exception e) {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }
        return foundUser;
    }

    public Optional<User> getSession(String auth) {
        String username = null;
        String jwt = null;
        if (auth != null && auth.startsWith("Bearer ")) {
            jwt = auth.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }
        if (username != null) {
            try {
                User user = (User) loadUserByUsername(username);
                if (jwtUtils.validateToken(jwt, user)) {
                    user.setJwt(jwt);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
