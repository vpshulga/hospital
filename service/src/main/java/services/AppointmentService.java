package services;

import entities.cards.Appointment;
import java.io.Serializable;
import java.util.List;

public interface AppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(Serializable id);

    void update(Appointment appointment);

    int delete(Serializable id);

    List<Appointment> getAllByPatientId(Serializable patientId);

    int deleteByPatId(Serializable id);
}
