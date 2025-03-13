package pl.wsis.dto.security;

import lombok.Data;
import pl.wsis.domain.Role;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
    private Role role;
}
