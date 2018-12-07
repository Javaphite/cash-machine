package ua.training.cashmachine.model.utils;

import ua.training.cashmachine.controller.Activity;
import ua.training.cashmachine.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class RoleUtils {

    public static List<String> getAllowedPaths(Activity... activitiesAllowed) {
        return Arrays.stream(activitiesAllowed)
                .map(Activity::getPath)
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public static List<String> getAllowedPaths(Role... adoptedRoles) {
        Set<String> adoptedPaths = new HashSet<>();
        for(Role adoptedRole: adoptedRoles) {
            adoptedPaths.addAll(adoptedRole.getPathsAllowed());
        }
        return Collections.unmodifiableList(new ArrayList<>(adoptedPaths));
    }

    public static List<String> getAllowedPaths(Role adoptedRole, Activity... activitiesAllowed) {
        List<String> paths = new ArrayList<>(adoptedRole.getPathsAllowed());
        paths.addAll(getAllowedPaths(activitiesAllowed));

        return Collections.unmodifiableList(paths);
    }

    public static List<String> getAllowedCommands(Activity... activitiesAllowed) {
        return Arrays.stream(activitiesAllowed)
                .map(Activity::getCommandMapping)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public static List<String> getAllowedCommands(Role... adoptedRoles) {
        Set<String> adoptedCommands = new HashSet<>();
        for(Role adoptedRole: adoptedRoles) {
            adoptedCommands.addAll(adoptedRole.getPathsAllowed());
        }
        return Collections.unmodifiableList(new ArrayList<>(adoptedCommands));
    }

    public static List<String> getAllowedCommands(Role adoptedRole, Activity... activitiesAllowed) {
        List<String> commands = new ArrayList<>(adoptedRole.getCommandsAllowed());
        commands.addAll(getAllowedPaths(activitiesAllowed));

        return Collections.unmodifiableList(commands);
    }
}
