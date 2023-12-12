package tech.na_app.model.profile.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EditInfoEducationRequest {

    @Schema(
            example = "1"
    )
    @NotNull
    private Integer id;

    @Schema(
            example = "1"
    )
    @NotNull
    private Integer userId;
    @Schema(
            example = "12312313",
            type = "string"
    )
    @NotEmpty
    private String certificate;

    @Schema(
            example = "Mechanic",
            type = "string"
    )
    @NotEmpty
    private String specialty;

    @Schema(
            example = "123132AC",
            type = "string"
    )
    @NotEmpty
    private String advanced_qualification;

}
