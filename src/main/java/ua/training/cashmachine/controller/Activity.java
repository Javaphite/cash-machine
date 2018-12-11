package ua.training.cashmachine.controller;

import ua.training.cashmachine.controller.command.AddProduct;
import ua.training.cashmachine.controller.command.CancelInvoice;
import ua.training.cashmachine.controller.command.ChangeLocale;
import ua.training.cashmachine.controller.command.CloseInvoice;
import ua.training.cashmachine.controller.command.CloseTurn;
import ua.training.cashmachine.controller.command.GetSupplies;
import ua.training.cashmachine.controller.command.HttpServletCommand;
import ua.training.cashmachine.controller.command.InvoiceMenu;
import ua.training.cashmachine.controller.command.JournalMenu;
import ua.training.cashmachine.controller.command.Login;
import ua.training.cashmachine.controller.command.LoginMenu;
import ua.training.cashmachine.controller.command.Logout;
import ua.training.cashmachine.controller.command.MainMenu;
import ua.training.cashmachine.controller.command.MakeReport;
import ua.training.cashmachine.controller.command.NewInvoice;
import ua.training.cashmachine.controller.command.OpenTurn;
import ua.training.cashmachine.controller.command.Refund;
import ua.training.cashmachine.controller.command.RemoveProduct;
import ua.training.cashmachine.controller.command.ReportsMenu;
import ua.training.cashmachine.controller.command.ShowInvoice;
import ua.training.cashmachine.controller.command.SuppliesMenu;
import ua.training.cashmachine.controller.command.UpdateSupplies;
import ua.training.cashmachine.exception.BadMappingException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Describes all user activities available with web-interface,
 * includes menu links and commands of that menus.
 */
public enum Activity {
    LOGIN_MENU(LoginMenu::new, "/"),
    MAIN_MENU(MainMenu::new, "/main"),
    JOURNAL_MENU(JournalMenu::new, "/journal"),
    SUPPLIES_MENU(SuppliesMenu::new, "/supplies"),
    REPORTS_MENU(ReportsMenu::new, "/reports"),
    INVOICE_MENU(InvoiceMenu::new, "/invoice"),
    CHANGE_LOCALE(ChangeLocale::new, "/", "changeLocale"),
    CHANGE_LOCALE_ALT(ChangeLocale::new, "/main", "changeLocale"),
    LOGIN(Login::new, "/", "login"),
    LOGOUT(Logout::new, "/main", "logout"),
    OPEN_TURN(OpenTurn::new, "/main", "openTurn"),
    CLOSE_TURN(CloseTurn::new, "/main", "closeTurn"),
    REFUND(Refund::new, "/main", "refund"),
    MAKE_REPORT(MakeReport::new, "/reports", "makeReport"),
    NEW_INVOICE(NewInvoice::new, "/invoice", "newInvoice"),
    CLOSE_INVOICE(CloseInvoice::new, "/invoice", "closeInvoice"),
    ADD_PRODUCT(AddProduct::new, "/invoice", "addProduct"),
    REMOVE_PRODUCT(RemoveProduct::new, "/invoice", "removeProduct"),
    CANCEL_INVOICE(CancelInvoice::new, "/invoice", "cancel"),
    SHOW_INVOICE(ShowInvoice::new, "/journal", "showInvoice"),
    GET_SUPPLIES(GetSupplies::new, "/supplies", "getSupplies"),
    UPDATE_SUPPLIES(UpdateSupplies::new, "/supplies", "updateSupplies");

    public static final String NO_COMMAND_MAPPING = "";

    private final HttpServletCommand command;
    private final String path;
    private final String commandMapping;

    Activity(Supplier<HttpServletCommand> commandSupplier, String path) {
        this(commandSupplier, path, NO_COMMAND_MAPPING);
    }

    Activity(Supplier<HttpServletCommand> commandSupplier, String path, String commandMapping) {
        this.command = commandSupplier.get();
        this.path = path;
        this.commandMapping = commandMapping;
    }

    /**
     * Static factory method for handing off the command instances
     * for given path and command mapping.
     * @param path path in Servlet context request came from;
     * @param commandMapping mapping for command;
     * @return appropriate command for given path and mapping.
     *
     * @throws BadMappingException if more than one mapping is suitable
     * for given path and command pair.
     */
    public static HttpServletCommand commandOf(String path, String commandMapping) {
        //TODO: add logging here
        String actualMapping = Objects.isNull(commandMapping)? NO_COMMAND_MAPPING: commandMapping;

        List<Activity> mappedActivities = Arrays.stream(values())
              .filter(activity -> Objects.equals(activity.path, path))
              .filter(activity -> Objects.equals(activity.commandMapping, actualMapping))
              .collect(Collectors.toList());

        if (1 < mappedActivities.size()) {
            throw new BadMappingException("Multiple mappings detected: path=" + path + ", command=" + actualMapping);
        }

        if (mappedActivities.isEmpty()) {
            throw new BadMappingException("No mappings found: path=" + path + ", command=" + actualMapping);
        }

        return mappedActivities.get(0).command;
    }

    public HttpServletCommand getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public String getCommandMapping() {
        return commandMapping;
    }
}
