package ua.training.cashmachine.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;

import java.util.Locale;

public class UserServiceTest {

    @Test
    void successfullyCreatesNewUserInDb() {
        // GIVEN:
        User user = new User();
        user.setLogin("a.nikoluk");
        user.setHash(DigestUtils.sha256Hex("Kastor001"));
        user.setRole(Role.SENIOR_CASHIER);
        user.setFirstName("Anna");
        user.setLastName("Nikoluk");
        UserService service = new UserService();

        // WHEN:
        service.createUser(user, Locale.US, "Ганна", "Ніколюк");
    }
    
}
