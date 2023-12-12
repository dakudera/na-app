package tech.na_app.model.profile.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RemoveInfoEducationRequest {

    @Schema(
            example = "1"
    )
    @NotEmpty
    private Integer id;
    @NotEmpty
    private Integer userId;

}
