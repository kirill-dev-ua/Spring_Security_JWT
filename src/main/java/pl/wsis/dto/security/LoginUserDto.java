package pl.wsis.dto.security;

import lombok.Data;

/**
 * @author Serhii Manko
 */
@Data
public class LoginUserDto {
    private String email;
    private String password;
}
