package web.command.enums;

import web.command.Controller;
import web.command.impl.*;

public enum CommandType {
    LOGIN("login/login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login/login.jsp", "Logout", "logout.title", new LogoutController()),
    CARD("card/main.jsp", "Card", "card.title", new CardController()),
    REGISTRATION("registration/main.jsp", "Registration", "registration.title", new RegistrationController()),
    DOCTOR("doctor/main.jsp", "Doctor", "doctor.title", new DoctorController()),
    CHECK("check/main.jsp", "Check", "check.title", new CheckController()),
    ADMIN("admin/main.jsp", "Admin", "admin.title", new AdminController()),
    WORKERS("workers/main.jsp", "Workers", "workers.title", new WorkersController()),
    CHANGE("login/change.jsp", "Change", "change.title", new ChangeController());

    CommandType(String pagePath, String pageName, String i18nKey, Controller controller) {
        this.pagePath = pagePath;
        this.pageName = pageName;
        this.i18nKey = i18nKey;
        this.controller = controller;

    }

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public String getPagePath() {
        return pagePath;
    }

    public String getPageName() {
        return pageName;
    }

    public Controller getController() {
        return controller;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public static CommandType getByPageName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return WORKERS;
    }
}
