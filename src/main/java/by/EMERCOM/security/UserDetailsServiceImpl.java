package by.EMERCOM.security;


import by.EMERCOM.model.domain.User;
import by.EMERCOM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(s);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(s);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getLogin())
                .password(user.get().getPassword())
                .roles(userRepository.getRole(user.get().getId())).build();
    }
}