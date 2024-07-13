package tech.na_app.services.user_profile;

import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.repository.UserRepository;

import java.util.Objects;

@Log4j2
public abstract class UserProfileAbs {

    @Autowired
    private UserRepository userRepository;

    protected User choosingUser(User user, String userIdStr) {
        if (Objects.nonNull(userIdStr)) {
            ObjectId userId = new ObjectId(userIdStr);
            User otherUser = userRepository.findById(userId).orElseThrow(() -> new ApiException(400, "User was not found"));
            log.info("Other user {}", otherUser);
            return otherUser;
        } else {
            log.info("Self user {}", user);
            return user;
        }
    }

}
