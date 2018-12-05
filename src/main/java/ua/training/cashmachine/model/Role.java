package ua.training.cashmachine.model;

import ua.training.cashmachine.controller.Activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    UNKNOWN_USER(Activity.TEST_ACTIVITY),
    CASHIER(),
    SENIOR_CASHIER(),
    MERCHANDISER(),
    MANAGER();

    private final List<String> pathsAllowed;
    private final List<String> commandsAllowed;

    Role(Activity... activitiesAllowed) {
        pathsAllowed = Arrays.stream(activitiesAllowed)
                .map(Activity::getPath)
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        commandsAllowed = Arrays.stream(activitiesAllowed)
                .map(Activity::getMapping)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public List<String> getCommandsAllowed() {
        return commandsAllowed;
    }

    public List<String> getPathsAllowed() {
        return pathsAllowed;
    }
}
