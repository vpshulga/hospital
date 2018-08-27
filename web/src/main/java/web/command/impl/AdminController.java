package web.command.impl;

import entities.Doctor;
import entities.Patient;
import entities.RegistryWorker;
import entities.User;
import enums.Educations;
import enums.Roles;
import enums.Sex;
import enums.Specialties;
import services.DoctorService;
import services.RegistryWorkerService;
import services.UserService;
import services.impl.DoctorServiceImpl;
import services.impl.RegistryWorkerServiceImpl;
import services.impl.UserServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminController implements Controller {
    private DoctorService doctorService = DoctorServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private RegistryWorkerService registryWorkerService = RegistryWorkerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (checkRole(req, resp)) return;

        deleteDoctor(req);
        deleteRegWorker(req);

        addDoctor(req);
        addRegWorker(req);

        List<Doctor> doctors = doctorService.getAll();
        List<RegistryWorker> registryWorkers = registryWorkerService.getAll();
        req.setAttribute("doctors", doctors);
        req.setAttribute("registryWorkers", registryWorkers);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);

    }

    private void addDoctor(HttpServletRequest req) {
        if (req.getParameter("firstName") != null && req.getParameter("lastName") != null) {
            User user = userService.save(new User());
            Doctor doctor = doctorService.save(new Doctor(req.getParameter("firstName"), req.getParameter("lastName"),
                    Integer.parseInt(req.getParameter("age")), Sex.valueOf(req.getParameter("sex")),
                    Educations.valueOf(req.getParameter("education")), Integer.parseInt(req.getParameter("experience")),
                    Specialties.valueOf(req.getParameter("spetiality")), user.getId()));
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            user.setRole(Roles.DOCTOR);
            if (!userService.getAllLogins().contains(user.getLogin())) {
                userService.update(user);
                boolean isCreatedDoc = true;
                req.setAttribute("isCreatedDoc", isCreatedDoc);
            } else {
                doctorService.delete(doctor.getId());
                userService.delete(user.getId());
                String reapetedUser = "Current user is exist";
                req.setAttribute("repDoc", reapetedUser);
            }
        }
    }

    private void addRegWorker(HttpServletRequest req) {
        if (req.getParameter("firstNameReg") != null && req.getParameter("lastNameReg") != null) {
            User user = userService.save(new User());
            RegistryWorker registryWorker = registryWorkerService.save(new RegistryWorker(req.getParameter("firstNameReg"), req.getParameter("lastNameReg"),
                    Integer.parseInt(req.getParameter("ageReg")), Sex.valueOf(req.getParameter("sexReg")),
                    Educations.valueOf(req.getParameter("educationReg")), Integer.parseInt(req.getParameter("experienceReg")),
                    user.getId()));
            user.setLogin(req.getParameter("loginReg"));
            user.setPassword(req.getParameter("passwordReg"));
            user.setRole(Roles.REG_WORKER);
            if (!userService.getAllLogins().contains(user.getLogin())) {
                userService.update(user);
                boolean isCreatedReg = true;
                req.setAttribute("isCreatedReg", isCreatedReg);
            } else {
                registryWorkerService.delete(registryWorker.getId());
                userService.delete(user.getId());
                String reapetedUser = "Current user is exist";
                req.setAttribute("repReg", reapetedUser);
            }
        }
    }

    private boolean checkRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && !(user.getRole().equals(Roles.ADMIN))) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=check");
            return true;
        }
        return false;
    }

    private void deleteDoctor(HttpServletRequest req) {
        if (req.getParameter("delDocId") != null) {
            Doctor doctor = doctorService.get(Integer.parseInt(req.getParameter("delDocId")));
            int deletedUserId = doctor.getUserId();
            doctorService.delete(Integer.parseInt(req.getParameter("delDocId")));
            userService.delete(deletedUserId);
            boolean docIsDeleted = true;
            req.setAttribute("isDocDeleted", docIsDeleted);
        }
    }

    private void deleteRegWorker(HttpServletRequest req) {
        if (req.getParameter("delRegId") != null) {
            RegistryWorker registryWorker = registryWorkerService.get(Integer.parseInt(req.getParameter("delRegId")));
            int deletedUserId = registryWorker.getUserId();
            registryWorkerService.delete(Integer.parseInt(req.getParameter("delRegId")));
            userService.delete(deletedUserId);
            boolean regIsDeleted = true;
            req.setAttribute("isRegDeleted", regIsDeleted);
        }
    }
}
