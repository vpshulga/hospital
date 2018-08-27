package services;

import entities.cards.Complaint;
import java.io.Serializable;

public interface ComplaintService {
    Complaint save(Complaint complaint);

    Complaint get(Serializable id);

    void update(Complaint complaint);

    int delete(Serializable id);
}
