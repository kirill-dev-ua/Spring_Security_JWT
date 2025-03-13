package pl.wsis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsis.domain.Role;
import pl.wsis.domain.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String fullName;
    private String password;
    private String email;
    private RoleDTO role;
    private Date createdAt;
    private Date updatedAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.role = new RoleDTO(user.getRole().getId(), user.getRole().getName());
    }

    public UserDTO(int id, String name) {
        this.id = id;
        this.fullName = name;
    }

    public UserDTO(int id, String name, Role role) {
        this.id = id;
        this.fullName = name;
        this.role = new RoleDTO(role.getId(), role.getName());
    }

    public UserDTO(long id, String fullName, String email,
                   Date createdAt, Date updatedAt, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = new RoleDTO(role.getId(), role.getName());
    }

    public UserDTO(int id, String name, String password) {
        this.id = id;
        this.fullName = name;
        this.password = password;
    }

    public UserDTO(String name, String password) {
        this.fullName = name;
        this.password = password;
    }
}
