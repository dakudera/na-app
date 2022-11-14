package tech.na_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.UserRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Integer id;
    private UserRole role;

    private ErrorObject error;

    public LoginResponse(ErrorObject error) {
        this.error = error;
    }
}
