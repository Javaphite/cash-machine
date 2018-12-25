package ua.training.cashmachine.model.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.training.cashmachine.model.annotation.Localized;

import java.util.Objects;

public class User {

    public static final String DEFAULT_HASH = "none";

    private int userId;
    private String login;
    private Role role;
    private String hash;
    @Localized private String firstName;
    @Localized private String lastName;
    private boolean expired;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ( (null == obj) || Objects.equals(getClass(), obj.getClass()) ) {
            return false;
        }

        User user = (User) obj;
        return (userId == user.userId) && Objects.equals(user.login, login);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("login", login)
                .append("role", role)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
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
