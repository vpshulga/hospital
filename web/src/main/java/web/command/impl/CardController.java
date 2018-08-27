package web.command.impl;

import entities.Patient;
import entities.User;
import entities.cards.Appointment;
import entities.cards.Diagnosys;
import enums.Roles;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AppointmentService;
import services.DiagnosysService;
import services.PatientService;
import services.impl.AppointmentServiceImpl;
import services.impl.DiagnosysServiceImpl;
import services.impl.PatientServiceImpl;
import web.command.Controller;

public class CardController implements Controller {
    private PatientService patientService = PatientServiceImpl.getInstance();
    private AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private DiagnosysService diagnosysService = DiagnosysServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && user.getRole().equals(Roles.PATIENT)) {
            Patient patient = patientService.getByUserId(user.getId());
            List<Appointment> appointments = appointmentService.getAllByPatientId(patient.getId());
            Diagnosys diagnosys = diagnosysService.getByPatientId(patient.getId());
            req.setAttribute("patient", patient);
            req.setAttribute("appointments", appointments);
            req.setAttribute("diagnosys", diagnosys);

        }
        if (user != null && (user.getRole().equals(Roles.DOCTOR)
                || user.getRole().equals(Roles.ADMIN))) {
            if (req.getParameter("id") != null) {
                Patient patient = patientService.get(Integer.parseInt(req.getParameter("id")));
                List<Appointment> appointments = appointmentService.getAllByPatientId(patient.getId());
                Diagnosys diagnosys = diagnosysService.getByPatientId(patient.getId());
                req.setAttribute("patient", patient);
                req.setAttribute("appointments", appointments);
                req.setAttribute("diagnosys", diagnosys);
            }
        }
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
