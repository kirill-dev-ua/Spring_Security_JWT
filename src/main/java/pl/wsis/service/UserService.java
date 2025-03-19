package pl.wsis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wsis.domain.Role;
import pl.wsis.domain.User;
import pl.wsis.dto.UserDTO;
import pl.wsis.exceptions.NoUsersFoundException;
import pl.wsis.repository.RoleRepository;
import pl.wsis.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        user.setEnabled(true);

        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAllUsers();

        if (users == null || users.isEmpty()) {
            throw new NoUsersFoundException("No users found in the system");
        }

        return users;
    }

    public User findByEmail(String currentUserEmail) {
        return userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public String blockUser(String email) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = findByEmail(currentUserEmail);
        User forBlock = findByEmail(email);
        if (currentUser.getEmail().equals(email)) {
            throw new NoUsersFoundException("Нельзя заблокировать самого себя");
        }
        if ("ADMIN".equals(forBlock.getRole().getName())) {
            throw new NoUsersFoundException("Нельзя заблокировать пользователя с ролью ADMIN");
        }
        forBlock.setEnabled(false);
        userRepository.save(forBlock);
        return "Пользователь заблокирован";
    }

    public String addManager(String email) {
        User user = findByEmail(email);
        if (user == null) {
            throw new NoUsersFoundException("Пользователь с таким email не найден");
        }
        if("ADMIN".equals(user.getRole().getName())){
            throw new NoUsersFoundException("Нельзя менять роль Админа");
        }
        Role managerRole = roleRepository.findByName("MANAGER")
                .orElseThrow(() -> new RuntimeException("Роль MANAGER не найдена"));

        user.setRole(managerRole);
        userRepository.save(user);
        return "Пользователю назначена роль менеджера";
    }
}
