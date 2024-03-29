package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveNewUserResponse {

    @Schema(
            example = "1"
    )
    private Integer id;
    private ErrorObject error;

    public SaveNewUserResponse(ErrorObject error) {
        this.error = error;
    }
}
