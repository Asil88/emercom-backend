package by.EMERCOM.model.request;

import lombok.Data;


@Data
public class RegistrationUser {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String country;
}
