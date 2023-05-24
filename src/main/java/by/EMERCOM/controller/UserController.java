package by.EMERCOM.controller;

import by.EMERCOM.exception.ResourceNotFoundException;
import by.EMERCOM.model.domain.User;
import by.EMERCOM.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAllUsers() {
        Optional<List<User>> optionalUsers = userService.findAllUsers();
        if (optionalUsers.isPresent()) {
            List<User> allUsers = optionalUsers.get();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("USERS NOT FOUND");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> userById = userService.getUserById(id);
        if (userById.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<HttpStatus> updateUserById(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.updateUserById(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
