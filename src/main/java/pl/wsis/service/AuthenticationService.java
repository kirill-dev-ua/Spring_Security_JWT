package pl.wsis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wsis.domain.Role;
import pl.wsis.domain.User;
import pl.wsis.dto.security.LoginUserDto;
import pl.wsis.dto.security.RegisterUserDto;
import pl.wsis.repository.RoleRepository;
import pl.wsis.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public User signup(RegisterUserDto dto) {
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new NullPointerException("Role not found with name: "
                        + dto.getRole().getName()));
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(role);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User login(LoginUserDto dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isPresent()) {
            User you = user.get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(you,
                            dto.getPassword()));
            return you;
        }
        return null;
    }

//    public User login(LoginUserDto dto) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
//        return userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UsernameNotFoundException(dto.getEmail()));
//    }
}
