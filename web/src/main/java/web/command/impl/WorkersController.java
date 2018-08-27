package web.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DoctorService;
import services.NurseService;
import services.RegistryWorkerService;
import services.impl.DoctorServiceImpl;
import services.impl.NurseSrviceImpl;
import services.impl.RegistryWorkerServiceImpl;
import web.command.Controller;

public class WorkersController implements Controller {
    private DoctorService doctorService = DoctorServiceImpl.getInstance();
    private NurseService nurseService = NurseSrviceImpl.getInstance();
    private RegistryWorkerService registryWorkerService = RegistryWorkerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.getServletContext().setAttribute("doctors", doctorService.getAll());
        session.getServletContext().setAttribute("nurses", nurseService.getAll());
        session.getServletContext().setAttribute("regWorkers", registryWorkerService.getAll());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);


    }
}
