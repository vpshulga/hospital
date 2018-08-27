package services;

import entities.cards.Diagnosys;
import java.io.Serializable;
import java.util.List;

public interface DiagnosysService {
    Diagnosys save(Diagnosys diagnosys);

    Diagnosys get(Serializable id);

    void update(Diagnosys diagnosys);

    int delete(Serializable id);

    List<Diagnosys> getAll();

    Diagnosys getByPatientId(Serializable patientId);

    List<String> getAllByText();

    int deleteByPatId(Serializable id);
}
