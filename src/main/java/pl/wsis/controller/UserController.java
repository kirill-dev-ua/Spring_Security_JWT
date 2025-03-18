package pl.wsis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.wsis.dto.UserDTO;
import pl.wsis.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

//    @PreAuthorize("hasPermission('READ')")
    @Secured("WRITE")
//    @PreAuthorize("hasAuthority('ADMIN')")

    // Любой пользователь у которого есть canBlockUser(Permission) не может блокнуть сам себя
    // Любой пользователь у которого есть canBlockUser(Permission) не может блокнуть пользователя с ролью ADMIN.
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
