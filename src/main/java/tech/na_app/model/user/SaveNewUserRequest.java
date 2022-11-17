package tech.na_app.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.UserRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveNewUserRequest {

    private String login;
    private String password;
    private UserRole role;
    private Integer companyId;

}
