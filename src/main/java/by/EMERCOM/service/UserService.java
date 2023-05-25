package by.EMERCOM.service;

import by.EMERCOM.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> findAllUsers();

    Optional<User> getUserById(int id);

    void updateUserById(User user);

    boolean deleteUserById(Integer id);
}
