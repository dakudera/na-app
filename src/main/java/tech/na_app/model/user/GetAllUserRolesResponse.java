package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.enums.UserRoleType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserRolesResponse {


    private List<Role> roles;

    private ErrorObject error;

    public GetAllUserRolesResponse(ErrorObject error) {
        this.error = error;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Role {

        @Schema(
                example = "EXECUTIVE_DIRECTOR"
        )
        private UserRoleType role;
        @Schema(
                example = "executive director"
        )
        private String description;
    }

}
