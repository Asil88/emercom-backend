package by.EMERCOM.controller;


import by.EMERCOM.model.request.RegistrationUser;
import by.EMERCOM.service.impl.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class SecurityController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SecurityServiceImpl securityServiceImpl;

    @Autowired
    public SecurityController(SecurityServiceImpl securityServiceImpl) {
        this.securityServiceImpl = securityServiceImpl;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid RegistrationUser registrationUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                logger.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        securityServiceImpl.registration(registrationUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
