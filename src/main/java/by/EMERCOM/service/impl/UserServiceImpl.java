package by.EMERCOM.service.impl;

import by.EMERCOM.model.domain.User;
import by.EMERCOM.repository.UserRepository;
import by.EMERCOM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<List<User>> findAllUsers() {
        return Optional.of((ArrayList<User>) userRepository.findAll());
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<List<User>>sortUserByName(String name){
        return Optional.of((ArrayList<User>) userRepository.findAllByName(name));
    }
    public Optional<List<User>>sortUserBySurname(String surname){
        return Optional.of((ArrayList<User>) userRepository.findAllBySurname(surname));
    }
    public Optional<List<User>>sortUserByCountry(String country){
        return Optional.of((ArrayList<User>) userRepository.findAllByCountry(country));
    }

    @Override
    public void updateUserById(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
