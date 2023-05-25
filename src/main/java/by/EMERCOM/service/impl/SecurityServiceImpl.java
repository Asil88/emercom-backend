package by.EMERCOM.service.impl;


import by.EMERCOM.model.domain.User;
import by.EMERCOM.model.request.RegistrationUser;
import by.EMERCOM.repository.UserRepository;
import by.EMERCOM.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean registration(RegistrationUser registrationUser) {
        try {
            User user = new User();
            user.setName(registrationUser.getName());
            user.setSurname(registrationUser.getSurname());
            user.setEmail(registrationUser.getEmail());
            user.setCountryOfResidence(registrationUser.getCountryOfResidence());
            user.setLogin(registrationUser.getLogin());
            user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));

            User savedUser = userRepository.save(user);
            userRepository.setUserRole(savedUser.getId());

            if (savedUser != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
