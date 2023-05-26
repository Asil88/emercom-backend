package by.EMERCOM.model.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Логин обязателен")
    @Size(min = 3, max = 16, message = "Логин должен быть не меньше 3 и не больше 16 символов")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Логин может содержать только буквенно-цифровые символы, символ подчеркивания и дефис.")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Неверный формат email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Имя обязательно")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия обязательна")
    @Column(name = "surname")
    private String surname;

    @Column(name = "country")
    private String country;


}
