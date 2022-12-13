package tech.na_app.model.role_buttons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.Buttons;
import tech.na_app.model.enums.UserRoleType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllowedButtonsResponse {

    private UserRoleType role;

    private List<Buttons> buttons;

    private ErrorObject errorObject;

    public GetAllowedButtonsResponse(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

}
