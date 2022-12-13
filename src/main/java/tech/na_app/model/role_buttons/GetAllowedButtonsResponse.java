package tech.na_app.model.role_buttons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.entity.role_buttons.Button;
import tech.na_app.model.ErrorObject;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllowedButtonsResponse {

    private List<Button> buttons;

    private ErrorObject errorObject;

    public GetAllowedButtonsResponse(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

}
