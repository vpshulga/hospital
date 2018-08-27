package services;

import entities.RegistryWorker;
import java.io.Serializable;
import java.util.List;

public interface RegistryWorkerService {
    RegistryWorker save(RegistryWorker registryWorker);

    RegistryWorker get(Serializable id);

    void update(RegistryWorker registryWorker);

    int delete(Serializable id);

    List<RegistryWorker> getAll();
}
