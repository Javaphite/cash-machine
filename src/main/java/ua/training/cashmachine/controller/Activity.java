package ua.training.cashmachine.controller;

import ua.training.cashmachine.controller.command.TestCommand;
import ua.training.cashmachine.controller.command.HttpServletCommand;
import ua.training.cashmachine.exception.MultipleMappingException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum Activity {
    TEST_ACTIVITY(TestCommand::new, "/", "");

    private final HttpServletCommand command;
    private final String path;
    private final String mapping;

    Activity(Supplier<HttpServletCommand> commandSupplier, String path, String mapping) {
        this.command = commandSupplier.get();
        this.path = path;
        this.mapping = mapping;
    }

    public static HttpServletCommand commandOf(String path, String mapping) {
        String actualMapping = Objects.isNull(mapping)? "": mapping;
        List<Activity> mappedActivities = Arrays.stream(values())
              .filter(activity -> Objects.equals(activity.getPath(), path))
              .filter(activity -> Objects.equals(activity.getMapping(), actualMapping))
              .collect(Collectors.toList());

        if (1 < mappedActivities.size()) {
            throw new MultipleMappingException(
                    "Multiple mappings detected: path=" + path + ", command=" + actualMapping);
        }

        return mappedActivities.get(0).getCommand();
    }

    public HttpServletCommand getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public String getMapping() {
        return mapping;
    }
}
