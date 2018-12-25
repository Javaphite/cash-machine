package ua.training.cashmachine.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;
import ua.training.cashmachine.l10n.LocalizationUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserServiceTest {

    @Test
    void successfullyCreatesNewUserInDb() {
        // GIVEN:
        User user = new User();
        user.setLogin("b.stryjenko");
        user.setHash(DigestUtils.sha256Hex("bstryjenko"));
        user.setRole(Role.MANAGER);
        user.setFirstName("Barbar1a");
        user.setLastName("Stryjen1ko");
        UserService service = new UserService();
        Map<Locale, Map<String, String>> localizationTable = new HashMap<>();
        localizationTable.put(Locale.forLanguageTag("en-US"),
                LocalizationUtils.getLocalizationMap(User.class, "Barbara", "Stryjenko"));
        localizationTable.put(Locale.forLanguageTag("uk-UA"),
                LocalizationUtils.getLocalizationMap(User.class, "Барбара", "Стриженко"));
        // WHEN:
        service.createUser(user, Locale.forLanguageTag("uk-UA"),localizationTable);
    }
}
