package web.command.impl;

import entities.Patient;
import entities.User;
import enums.Roles;
import enums.Sex;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.DoctorService;
import services.PatientService;
import services.UserService;
import services.impl.DoctorServiceImpl;
import services.impl.PatientServiceImpl;
import services.impl.UserServiceImpl;
import web.command.Controller;

public class RegistrationController implements Controller {
    private PatientService patientService = PatientServiceImpl.getInstance();
    private DoctorService doctorService = DoctorServiceImpl.getInstance();
//    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        if (checkRole(req, resp)) return;
//
//        addPatient(req);

        List doctors = doctorService.getAll();
        req.setAttribute("doctors", doctors);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }

//    private void addPatient(HttpServletRequest req) {
//        if (req.getParameter("firstName") != null && req.getParameter("lastName") != null) {
////            User user = userService.create(new User());
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String currentDateTime = sdf.format(date);
//            Patient patient = patientService.save(new Patient(req.getParameter("firstName"), req.getParameter("lastName"),
//                    Integer.parseInt(req.getParameter("age")), Sex.valueOf(req.getParameter("sex")),
//                    req.getParameter("city"), req.getParameter("street"), Integer.parseInt(req.getParameter("house")),
//                    Integer.parseInt(req.getParameter("apartament")), req.getParameter("complaint"),
//                    Integer.parseInt(req.getParameter("doctorId")), currentDateTime, user.getId()));
//            String password = generatePassword();
//            String userName = "pat" + patient.getId();
//            user.setLogin(userName);
//            user.setPassword(password);
//            user.setRole(Roles.PATIENT);
//            userService.update(user);
//            req.setAttribute("userName", userName);
//            req.setAttribute("password", password);
//        }
//    }
//
//    private boolean checkRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        User user = (User) req.getSession().getAttribute("user");
//        if (user != null && !(user.getRole().equals(Roles.REG_WORKER) || user.getRole().equals(Roles.ADMIN))) {
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=check");
//            return true;
//        }
//        return false;
//    }
//
//    private String generatePassword() {
//        String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_$";
//        Random random = new Random();
//        StringBuilder pass = new StringBuilder();
//        for (int i = 0; i < 8; i++) {
//            pass.append(template.charAt(random.nextInt(template.length())));
//        }
//        return pass.toString();
//    }

}
