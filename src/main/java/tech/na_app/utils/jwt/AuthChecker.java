package tech.na_app.utils.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.enums.UserRole;
import tech.na_app.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthChecker {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User checkToken(String token, UserRole role) throws ApiException {
        String tokenSplit = token.split(" ")[1];

        Claims claims = jwtUtil.extractAllClaims(tokenSplit);
        Integer userId = (Integer) claims.get("userId");
        Optional<User> byId = userRepository.findById(userId);

        if (byId.isEmpty()) {
            throw new ApiException(500, "");
        }

        User user = byId.get();
        if (user.getRole().getValue() > role.getValue()) {
            throw new ApiException(500, "denied");
        }

        return user;
    }


}
