package services;

import entities.Doctor;
import java.io.Serializable;
import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);

    Doctor get(Serializable id);

    void update(Doctor doctor);

    int delete(Serializable id);

    List<Doctor> getAll();

    Doctor getDoctorByUID(Serializable userId);
}
