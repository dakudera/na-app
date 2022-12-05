package tech.na_app.services.role_buttons;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.role_buttons.RoleButtons;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.role_buttons.GetAllowedButtonsResponse;
import tech.na_app.repository.RoleButtonsRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleButtonsService {

    private final RoleButtonsRepository roleButtonsRepository;

    public GetAllowedButtonsResponse getAllowedButtons(String requestId, User user){
        try {
            Optional<RoleButtons> allowedRoleButtons = roleButtonsRepository.findByRole(user.getRole());
            if (allowedRoleButtons.isEmpty()) {
                log.info(requestId + " Allowed buttons were not found");
                return new GetAllowedButtonsResponse(new ErrorObject(0));
            }
            return GetAllowedButtonsResponse.builder()
                    .role(allowedRoleButtons.get().getRole())
                    .buttons(allowedRoleButtons.get().getAllowed_buttons())
                    .errorObject(new ErrorObject(0))
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new GetAllowedButtonsResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
