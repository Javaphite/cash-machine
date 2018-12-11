package ua.training.cashmachine.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.training.cashmachine.controller.command.HttpServletCommand;
import ua.training.logger.TestLifecycleLogger;

class ActivityTest extends TestLifecycleLogger {

    @Test
    void mustReturnCommandByPathAndEmptyMapping() {
        HttpServletCommand command = Activity.commandOf("/", "");
        Assertions.assertEquals(command, Activity.LOGIN_MENU.getCommand());
    }

    @Test
    void mustReturnCommandByPathAndNullMapping() {
        HttpServletCommand command = Activity.commandOf("/main", null);
        Assertions.assertEquals(command, Activity.MAIN_MENU.getCommand());
    }

    @Test
    void mustReturnCommandByPathAndNonNullMapping() {
        HttpServletCommand command = Activity.commandOf("/", "login");
        Assertions.assertEquals(command, Activity.LOGIN.getCommand());
    }
}
