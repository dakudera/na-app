package tech.na_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.entity.user.UserSequence;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.user.TestAuthRequest;
import tech.na_app.model.user.TestAuthResponse;
import tech.na_app.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;


    public TestAuthResponse auth(TestAuthRequest testAuthRequest) {
        if (testAuthRequest.getLogin() == null || testAuthRequest.getLogin().isEmpty()
                || testAuthRequest.getPassword() == null || testAuthRequest.getPassword().isEmpty()) {
            return new TestAuthResponse(new ErrorObject(400, "BAD REQUEST"));
        }

        UserSequence sequenceNumber = (UserSequence) sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME, UserSequence.class);

        User save = userRepository.save(
                User.builder()
                        .id(sequenceNumber.getSeq())
                        .login(testAuthRequest.getLogin())
                        .password(testAuthRequest.getPassword())
                        .role(testAuthRequest.getRole())
                        .build()
        );

        return TestAuthResponse.builder()
                .id(save.getId())
                .login(save.getLogin())
                .password(save.getPassword())
                .role(save.getRole())
                .build();
    }


}
