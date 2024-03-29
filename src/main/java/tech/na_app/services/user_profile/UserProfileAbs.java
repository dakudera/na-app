package tech.na_app.services.user_profile;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.repository.UserRepository;

import java.util.Objects;

@Log4j2
public abstract class UserProfileAbs {

    @Autowired
    private UserRepository userRepository;

     public User choosingUser(User user, Integer userId) {
        if (Objects.nonNull(userId)) {
            User otherUser = userRepository.findById(userId).orElseThrow(() -> new ApiException(400, "User was not found"));
            log.info("Other user {}", otherUser);
            return otherUser;
        } else {
            log.info("Self user {}", user);
            return user;
        }
    }

}
