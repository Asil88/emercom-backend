package by.EMERCOM.controller;

import by.EMERCOM.exception.ResourceNotFoundException;
import by.EMERCOM.mapper.UserToResponseUserMapper;
import by.EMERCOM.model.domain.User;
import by.EMERCOM.model.response.UserResponse;
import by.EMERCOM.model.util.SortValueEnum;
import by.EMERCOM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    private UserToResponseUserMapper userToResponseUserMapper;

    @Autowired
    public UserController(UserService userService, UserToResponseUserMapper userToResponseUserMapper) {
        this.userService = userService;
        this.userToResponseUserMapper = userToResponseUserMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> findAllUsers() {
        Optional<List<User>> optionalUsers = userService.findAllUsers();
        if (optionalUsers.isPresent()) {
            List<User> allUsers = optionalUsers.get();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("USERS NOT FOUND");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        Optional<User> userById = userService.getUserById(id);
        if (userById.isPresent()) {
            User user = userById.get();
            UserResponse userResponse = userToResponseUserMapper.userToResponse(user);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    @GetMapping("/sort/{params}")
    public ResponseEntity<List<User>> sortUserByValue(@PathVariable SortValueEnum params, @RequestParam String value ) {
        Optional<List<User>> optionalUsers;

        switch (params) {
            case Country:
                optionalUsers = userService.sortUserByCountry(value);
                break;
            case Name:
                optionalUsers = userService.sortUserByName(value);
                break;
            case Surname:
                optionalUsers = userService.sortUserBySurname(value);
                break;
            default:
                throw new ResourceNotFoundException("VALUE NOT FOUND");
        }

        if (optionalUsers.isPresent()) {
            List<User> sortedUsers = optionalUsers.get();
            return new ResponseEntity<>(sortedUsers, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("VALUE NOT FOUND");
        }
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
