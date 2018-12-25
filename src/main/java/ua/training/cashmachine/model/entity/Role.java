package ua.training.cashmachine.model.entity;

import ua.training.cashmachine.controller.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.training.cashmachine.controller.Activity.*;

public enum Role {
    UNKNOWN_USER(LOGIN_MENU, LOGIN, CHANGE_LOCALE),
    CASHIER(MAIN_MENU, INVOICE_MENU, LOGOUT, CHANGE_LOCALE_ALT, ADD_PRODUCT, CLOSE_INVOICE),
    SENIOR_CASHIER(Role.CASHIER, REPORTS_MENU, JOURNAL_MENU, REMOVE_PRODUCT, CANCEL_INVOICE,
            OPEN_TURN, CLOSE_TURN, MAKE_REPORT, REFUND, SHOW_INVOICE),
    MERCHANDISER(MAIN_MENU, SUPPLIES_MENU, LOGOUT, CHANGE_LOCALE_ALT, GET_SUPPLIES, UPDATE_SUPPLIES),
    MANAGER(Role.CASHIER, Role.SENIOR_CASHIER, Role.MERCHANDISER);

    private final List<String> pathsAllowed;
    private final List<String> commandsAllowed;

    Role(Activity... activitiesAllowed) {
        pathsAllowed = evaluateAllowedPaths(activitiesAllowed);
        commandsAllowed = evaluateAllowedCommands(activitiesAllowed);
    }

    Role(Role... adoptedRoles) {
        pathsAllowed = evaluateAllowedPaths(adoptedRoles);
        commandsAllowed = evaluateAllowedCommands(adoptedRoles);
    }

    Role(Role adoptedRole, Activity... activitiesAllowed) {
        pathsAllowed = evaluateAllowedPaths(adoptedRole, activitiesAllowed);
        commandsAllowed = evaluateAllowedCommands(adoptedRole, activitiesAllowed);
    }

    public List<String> getCommandsAllowed() {
        return commandsAllowed;
    }

    public List<String> getPathsAllowed() {
        return pathsAllowed;
    }

    private static List<String> evaluateAllowedPaths(Activity... activitiesAllowed) {
        return Arrays.stream(activitiesAllowed)
                .map(Activity::getPath)
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private static List<String> evaluateAllowedPaths(Role... adoptedRoles) {
        Set<String> adoptedPaths = new HashSet<>();
        for(Role adoptedRole: adoptedRoles) {
            adoptedPaths.addAll(adoptedRole.pathsAllowed);
        }
        return Collections.unmodifiableList(new ArrayList<>(adoptedPaths));
    }

    private static List<String> evaluateAllowedPaths(Role adoptedRole, Activity... activitiesAllowed) {
        List<String> paths = new ArrayList<>(adoptedRole.pathsAllowed);
        paths.addAll(evaluateAllowedPaths(activitiesAllowed));

        return Collections.unmodifiableList(paths);
    }

    private static List<String> evaluateAllowedCommands(Activity... activitiesAllowed) {
        return Arrays.stream(activitiesAllowed)
                .map(Activity::getCommandMapping)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private static List<String> evaluateAllowedCommands(Role... adoptedRoles) {
        Set<String> adoptedCommands = new HashSet<>();
        for(Role adoptedRole: adoptedRoles) {
            adoptedCommands.addAll(adoptedRole.pathsAllowed);
        }
        return Collections.unmodifiableList(new ArrayList<>(adoptedCommands));
    }

    private static List<String> evaluateAllowedCommands(Role adoptedRole, Activity... activitiesAllowed) {
        List<String> commands = new ArrayList<>(adoptedRole.commandsAllowed);
        commands.addAll(evaluateAllowedPaths(activitiesAllowed));

        return Collections.unmodifiableList(commands);
    }
}
