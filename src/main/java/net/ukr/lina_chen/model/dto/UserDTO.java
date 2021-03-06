package net.ukr.lina_chen.model.dto;

import net.ukr.lina_chen.model.entity.Role;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Long id;
    private Role role;
    private String password;
    private String email;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, Role role, String password, String email, String name) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder {
        private Long id;
        private Role role;
        private String password;
        private String email;
        private String name;

        private Builder() {
        }

        public static UserDTO.Builder anUserDTO() {
            return new UserDTO.Builder();
        }

        public UserDTO.Builder withRoles(Role roles) {
            this.role = roles;
            return this;
        }

        public UserDTO.Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDTO.Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTO.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UserDTO.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.setRole(role);
            userDTO.setPassword(password);
            userDTO.setEmail(email);
            userDTO.setName(name);
            userDTO.setId(id);
            return userDTO;
        }
    }
}
