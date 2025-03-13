package pl.wsis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wsis.domain.Role;
import pl.wsis.domain.User;
import pl.wsis.dto.UserDTO;
import pl.wsis.exceptions.NoUsersFoundException;
import pl.wsis.repository.RoleRepository;
import pl.wsis.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void saveWithRole(UserDTO dto) {
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new NullPointerException("Role not found with name: "
                        + dto.getRole().getName()));
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAllUsers();

        if (users == null || users.isEmpty()) {
            throw new NoUsersFoundException("No users found in the system");
        }

        return users;
    }
}
