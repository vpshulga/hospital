package web.command.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entities.User;
import enums.Roles;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;
import services.impl.UserServiceImpl;
import web.command.Controller;


public class LoginController implements Controller {
//    private UserService userService = UserServiceImpl.getInstance();
    private UserDAO userDAO = new UserDAOImpl(User.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "Login form");
            dispatcher.forward(req, resp);
            return;
        }


        User user = userDAO.findOne((long)1);
        if (user != null && password.equals(user.getPassword()) && user.getRole().equals(Roles.ADMIN)) {
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=admin");
        }
//        if (user != null && user.getPassword().equals(Encoder.encode(password))) {
//        if (user != null && password.equals(user.getPassword()) && user.getRole().equals(Roles.REG_WORKER)) {
//            req.getSession().setAttribute("user", user);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=registration");
//            return;
//        } else if (user != null && password.equals(user.getPassword()) && user.getRole().equals(Roles.DOCTOR)) {
//            req.getSession().setAttribute("user", user);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=doctor");
//            return;
//        } else if (user != null && password.equals(user.getPassword()) && user.getRole().equals(Roles.PATIENT)) {
//            req.getSession().setAttribute("user", user);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=card");
//            return;
//        } else if (user != null && password.equals(user.getPassword()) && user.getRole().equals(Roles.ADMIN)) {
//            req.getSession().setAttribute("user", user);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=admin");
//        } else {
//            req.setAttribute("errorMsg", "Invalid Login or Password");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
//            req.setAttribute("title", "Login form");
//            dispatcher.forward(req, resp);
//            return;
//        }
    }
}

