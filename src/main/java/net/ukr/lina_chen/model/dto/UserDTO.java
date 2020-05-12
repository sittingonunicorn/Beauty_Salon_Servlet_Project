package net.ukr.lina_chen.model.dto;

import net.ukr.lina_chen.model.entity.Role;

public class UserDTO {
    private Role role;
    private String password;
    private String email;
    private String name;
    private String nameUkr;

    public UserDTO() {
    }

    public UserDTO(Role role, String password, String email, String name, String nameUkr) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nameUkr = nameUkr;
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

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    @Override
    public String toString() {
        return "UserDTO{role=" + role +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static final class Builder {
        private Role role;
        private String password;
        private String email;
        private String name;
        private String nameUkr;

        private Builder() {
        }

        public static UserDTO.Builder anUserDTO() {
            return new UserDTO.Builder();
        }
        
        public UserDTO.Builder withRole(Role role) {
            this.role = role;
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

        public UserDTO.Builder withNameUkr(String nameUkr) {
            this.nameUkr = nameUkr;
            return this;
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.setRole(role);
            userDTO.setPassword(password);
            userDTO.setEmail(email);
            userDTO.setName(name);
            userDTO.setNameUkr(nameUkr);
            return userDTO;
        }
    }
}
