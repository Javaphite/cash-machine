package ua.training.cashmachine.model.entity;

import ua.training.cashmachine.controller.Activity;
import ua.training.cashmachine.model.utils.RoleUtils;

import java.util.List;

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
        pathsAllowed = RoleUtils.getAllowedPaths(activitiesAllowed);
        commandsAllowed = RoleUtils.getAllowedCommands(activitiesAllowed);
    }

    Role(Role... adoptedRoles) {
        pathsAllowed = RoleUtils.getAllowedPaths(adoptedRoles);
        commandsAllowed = RoleUtils.getAllowedCommands(adoptedRoles);
    }

    Role(Role adoptedRole, Activity... activitiesAllowed) {
        pathsAllowed = RoleUtils.getAllowedPaths(adoptedRole, activitiesAllowed);
        commandsAllowed = RoleUtils.getAllowedCommands(adoptedRole, activitiesAllowed);
    }

    public List<String> getCommandsAllowed() {
        return commandsAllowed;
    }

    public List<String> getPathsAllowed() {
        return pathsAllowed;
    }
}
