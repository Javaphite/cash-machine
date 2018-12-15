package ua.training.cashmachine.model.entity;

import ua.training.cashmachine.model.annotation.Localized;
import ua.training.cashmachine.model.annotation.WriteOnly;

public class User {

    public static final String DEFAULT_HASH = "none";

    private int userId;
    private String login;
    private Role role;
    @WriteOnly private String hash;
    @Localized private String firstName;
    @Localized private String lastName;
    @WriteOnly private boolean expired;

    //TODO: rewrite equals and hashcode
    @Override
    public boolean equals(Object obj) {
       return false;
    }

    @Override
    public int hashCode() {
       return 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
