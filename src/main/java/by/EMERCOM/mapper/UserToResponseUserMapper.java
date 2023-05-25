package by.EMERCOM.mapper;

import by.EMERCOM.model.domain.User;
import by.EMERCOM.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseUserMapper {
    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setCountry(user.getCountry());
        return userResponse;
    }
}

