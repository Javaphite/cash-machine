package ua.training.cashmachine.l10n;

import ua.training.cashmachine.model.annotation.Localized;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public final class LocalizationUtils {

    private LocalizationUtils() {}

    public static Map<String, String> getEmptyLocalizationMap(Class<?> clazz) {
        Map<String, String> localizationMap = new LinkedHashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(Localized.class)) {
                localizationMap.put(field.getName(), null);
            }
        }
        return localizationMap;
    }

    public static Map<String, String> getLocalizationMap(Class<?> clazz, String... localizedValues) {
        Map<String, String> localizationMap = getEmptyLocalizationMap(clazz);
        if (localizationMap.size() == localizedValues.length) {
            String[] keys = new String[0];
            keys = localizationMap.keySet().toArray(keys);
            for (int i=0; i < localizedValues.length; i++) {
                localizationMap.replace(keys[i], localizedValues[i]);
            }
        } else {
            throw new IllegalArgumentException("Actual number of localized values differs from required!");
        }
        return localizationMap;
    }
}
