package services;

import entities.User;
import java.io.Serializable;
import java.util.List;

public interface UserService {
    User save(User user);

    User get(Serializable id);

    void update(User user);

    int delete(Serializable id);

    User getByLogin(String login);

    List<String> getAllLogins();
}
