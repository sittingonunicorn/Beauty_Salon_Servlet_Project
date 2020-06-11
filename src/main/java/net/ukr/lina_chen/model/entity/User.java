package net.ukr.lina_chen.model.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private Role role;
    private String password;
    private String email;
    private String name;
    private String nameUkr;

    public User() {
    }

    public User(Long id, Role role, String password, String email, String name, String nameUkr) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nameUkr = nameUkr;
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

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    @Override
    public String toString() {
        return "User{" +
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
        private String nameUkr;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withRole(Role roles) {
            this.role = roles;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNameUkr(String nameUkr) {
            this.nameUkr = nameUkr;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setRole(role);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setNameUkr(nameUkr);
            return user;
        }
    }
}
