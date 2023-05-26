package by.EMERCOM.service;

import by.EMERCOM.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> findAllUsers();

    Optional<User> getUserById(int id);

    void updateUserById(User user);

    boolean deleteUserById(Integer id);

    Optional<List<User>> sortUserByName(String name);

    Optional<List<User>> sortUserBySurname(String surname);

    Optional<List<User>> sortUserByCountry(String country);
}
