package web.command.impl;

import entities.Doctor;
import entities.Patient;
import entities.User;
import entities.cards.Appointment;
import entities.cards.Diagnosys;
import enums.AppointmentsType;
import enums.Roles;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.*;
import services.impl.*;
import web.command.Controller;

public class DoctorController implements Controller {
    private AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private PatientService patientService = PatientServiceImpl.getInstance();
    private DoctorService doctorService = DoctorServiceImpl.getInstance();
    private DiagnosysService diagnosysService = DiagnosysServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Doctor doc = null;

        if (checkRole(req, resp)) return;

        deletePatient(req);

        addAppointment(req);

        addOrUpdateDiagnosys(req);

        getPatients(req, doc);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }

    private boolean checkRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && !(user.getRole().equals(Roles.DOCTOR))) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=check");
            return true;
        }
        return false;
    }

    private void getPatients(HttpServletRequest req, Doctor doc) {
        List<Patient> patients = new CopyOnWriteArrayList<>();
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            doc = doctorService.getDoctorByUID(user.getId());
        }
        if (doc != null) {
            patients = patientService.getAllByDoctorId(doc.getId());
        }
        req.setAttribute("patients", patients);
    }

    private void addOrUpdateDiagnosys(HttpServletRequest req) {
        List<Diagnosys> diagnoses = diagnosysService.getAll();
        if (req.getParameter("patientDiaId") != null && req.getParameter("diagnosys") != null) {
            Diagnosys diagnosys = diagnosysService.getByPatientId(Integer.parseInt(req.getParameter("patientDiaId")));
            int count = 0;
            if (diagnoses.size() > 0) {
                for (Diagnosys d : diagnoses) {
                    if (d.equals(diagnosys)) {
                        count++;
                    }
                }
            }
            if (count == 0) {
                Diagnosys doneDiagnosys = diagnosysService.save(new Diagnosys(patientService.get(Integer.parseInt(req.getParameter("patientDiaId"))),
                        req.getParameter("diagnosys")));
                req.setAttribute("doneDiagnosys", doneDiagnosys);

            } else {
                diagnosys.setText(req.getParameter("diagnosys"));
                diagnosysService.update(diagnosys);
                req.setAttribute("updatedDiagnosys", diagnosys);
            }
        }
    }

    private void addAppointment(HttpServletRequest req) {
        if (req.getParameter("patientAppId") != null && req.getParameter("appointment") != null
                && !"".equals(req.getParameter("appointment"))) {
            Appointment doneAppointment = appointmentService.save(new Appointment(patientService.get(Integer.parseInt(req.getParameter("patientAppId"))),
                    AppointmentsType.valueOf(req.getParameter("appointmentType")),
                    req.getParameter("appointment")));
            req.setAttribute("doneAppointment", doneAppointment);
        }
    }


    private void deletePatient(HttpServletRequest req) {
        if (req.getParameter("delPatId") != null) {
            Patient patient = patientService.get(Integer.parseInt(req.getParameter("delPatId")));
            int deletedUserId = patient.getUserId();
            appointmentService.deleteByPatId(patient.getId());
            diagnosysService.deleteByPatId(patient.getId());
            patientService.delete(Integer.parseInt(req.getParameter("delPatId")));
            userService.delete(deletedUserId);
            boolean isDeleted = true;
            req.setAttribute("isDeleted", isDeleted);
        }
    }
}
