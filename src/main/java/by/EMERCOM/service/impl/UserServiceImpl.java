package by.EMERCOM.service.impl;

import by.EMERCOM.model.domain.User;
import by.EMERCOM.repository.UserRepository;
import by.EMERCOM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<User>> findAllUsers() {
        return Optional.of((ArrayList<User>) userRepository.findAll());
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserById(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        Optional<User> optionalOrder = userRepository.findById(id);
        try {
            if (optionalOrder.isPresent()) {
                userRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
