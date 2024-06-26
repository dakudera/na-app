package tech.na_app.utils.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthChecker {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User checkToken(String token, UserRoleType role) throws ApiException {
        String tokenSplit = token.split(" ")[1];

        Claims claims = jwtUtil.extractAllClaims(tokenSplit);
        String userIdStr = (String) claims.get("userId");
        ObjectId userId = new ObjectId(userIdStr);
        Optional<User> userById = userRepository.findById(userId);

        if (userById.isEmpty()) {
            throw new ApiException(500, "");
        }

        User user = userById.get();
        if (user.getRole().getValue() > role.getValue()) {
            throw new ApiException(500, "denied");
        }

        return user;
    }


}
