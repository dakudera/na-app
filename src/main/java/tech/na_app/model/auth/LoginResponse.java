package tech.na_app.model.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @Schema(
            example = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWt1ZGVyYSIsInJvbGUiOiJTVVBFUl9BRE1JTiIsImV4cCI6MTY2ODUwNTkyN ..."
    )
    private String accessToken;
    @Schema(
            example = "dfd74a32-8573-47df-9b3d-943b4d864fa7"
    )
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023'T'10:00",
            pattern = "dd.MM.yyyy'T'HH:mm",
            type = "string"
    )
    private Date issueAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023'T'11:00",
            pattern = "dd.MM.yyyy'T'HH:mm",
            type = "string"
    )
    private Date expDate;

    @Schema(
            example = "DRIVER",
            type = "string"
    )
    private UserRoleType role;

    private ErrorObject error;

    public LoginResponse(ErrorObject error) {
        this.error = error;
    }
}
