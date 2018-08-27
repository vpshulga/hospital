package services;

import entities.Patient;
import java.io.Serializable;
import java.util.List;

public interface PatientService {
    Patient save(Patient patient);

    Patient get(Serializable id);

    void update(Patient patient);

    int delete(Serializable id);

    List<Patient> getAllByDoctorId(Serializable doctorId);

    Patient getByUserId(Serializable userId);
}
