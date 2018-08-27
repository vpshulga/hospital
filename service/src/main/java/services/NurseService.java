package services;

import entities.Nurse;
import java.io.Serializable;
import java.util.List;

public interface NurseService {
    Nurse save(Nurse nurse);

    Nurse get(Serializable id);

    void update(Nurse nurse);

    int delete(Serializable id);

    List<Nurse> getAll();
}
