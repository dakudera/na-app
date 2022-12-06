package tech.na_app.services.role_buttons;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.role_buttons.RoleButtons;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.Buttons;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.role_buttons.GetAllowedButtonsResponse;
import tech.na_app.repository.RoleButtonsRepository;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleButtonsService {

    private final RoleButtonsRepository roleButtonsRepository;

    public GetAllowedButtonsResponse getAllowedButtons(String requestId, User user){
        try {
            RoleButtons allowedRoleButtons = roleButtonsRepository.findByRole(user.getRole())
                    .orElseGet(()-> RoleButtons.builder()
                            .role(UserRoleType.UNKNOWN)
                            .allowed_buttons(List.of(Buttons.values()))
                            .build());

            return GetAllowedButtonsResponse.builder()
                    .role(allowedRoleButtons.getRole())
                    .buttons(allowedRoleButtons.getAllowed_buttons())
                    .errorObject(new ErrorObject(0))
                    .build();
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetAllowedButtonsResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
