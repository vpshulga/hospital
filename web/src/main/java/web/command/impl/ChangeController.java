package web.command.impl;

import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;
import services.impl.UserServiceImpl;
import web.command.Controller;

public class ChangeController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        String oldPass = req.getParameter("oldPassword");
        String newPass = req.getParameter("newPassword");
        String confirm = req.getParameter("confirm");


        if (user != null) {
            if (oldPass != null && oldPass.equals(user.getPassword())) {
                if (newPass != null && newPass.equals(confirm)) {
                    user.setPassword(newPass);
                    userService.update(user);
                    req.setAttribute("successChange", "Пароль изменен");
                } else {
                    req.setAttribute("noSuccessChange", "Пароли не совпадают");
                }
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
