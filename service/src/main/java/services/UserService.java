package services;

import entities.User;
import java.io.Serializable;
import java.util.List;

public interface UserService {
    void create(User user);

    User findOne(Serializable id);

    void update(User user);

    void delete(User user);

    void deleteById(Long id);

}
