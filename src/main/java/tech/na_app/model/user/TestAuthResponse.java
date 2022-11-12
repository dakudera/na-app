package tech.na_app.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestAuthResponse {

    private Integer id;
    private String login;
    private String password;
    private UserRole role;

    private ErrorObject error;

    public TestAuthResponse(ErrorObject error) {
        this.error = error;
    }
}
