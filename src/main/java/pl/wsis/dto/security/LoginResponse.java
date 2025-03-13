package pl.wsis.dto.security;

import lombok.Data;

/**
 * @author Serhii Manko
 */
@Data
public class LoginResponse {
    private String token;
    private Long expiresIn;
}
