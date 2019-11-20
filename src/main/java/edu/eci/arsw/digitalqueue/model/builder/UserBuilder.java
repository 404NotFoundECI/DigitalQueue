package edu.eci.arsw.digitalqueue.model.builder;

import edu.eci.arsw.digitalqueue.model.Role;
import edu.eci.arsw.digitalqueue.model.User;

import java.util.Set;

public final class UserBuilder {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String password;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        return user;
    }
}
