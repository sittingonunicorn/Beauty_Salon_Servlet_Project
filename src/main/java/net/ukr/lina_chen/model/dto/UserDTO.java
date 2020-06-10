package net.ukr.lina_chen.model.dto;

import net.ukr.lina_chen.model.entity.Role;

import java.io.Serializable;
import java.util.Set;

public class UserDTO implements Serializable {
    private Long id;
    private Set<Role> roles;
    private String password;
    private String email;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, Set<Role> roles, String password, String email, String name) {
        this.id = id;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
        return "UserDTO{role=" + roles +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static final class Builder {
        private Long id;
        private Set<Role> roles;
        private String password;
        private String email;
        private String name;

        private Builder() {
        }

        public static UserDTO.Builder anUserDTO() {
            return new UserDTO.Builder();
        }
        
        public UserDTO.Builder withRoles(Set<Role> roles) {
            this.roles = roles;
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
            userDTO.setRoles(roles);
            userDTO.setPassword(password);
            userDTO.setEmail(email);
            userDTO.setName(name);
            userDTO.setId(id);
            return userDTO;
        }
    }
}
