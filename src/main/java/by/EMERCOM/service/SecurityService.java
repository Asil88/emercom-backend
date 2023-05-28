package by.EMERCOM.service;

import by.EMERCOM.model.request.RegistrationUser;

public interface SecurityService {
    boolean registration(RegistrationUser registrationUser);

    boolean login(String username, String password);

    void logout();
}
