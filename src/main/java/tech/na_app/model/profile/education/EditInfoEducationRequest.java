package tech.na_app.model.profile.education;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record EditInfoEducationRequest(
        @Schema(
                example = "1"
        )
        @NotNull
        String id,

        @Schema(
                example = "1"
        )
        @NotNull
        String userId,
        @Schema(
                example = "12312313",
                type = "string"
        )
        @NotEmpty
        String certificate,

        @Schema(
                example = "Mechanic",
                type = "string"
        )
        @NotEmpty
        String specialty,

        @Schema(
                example = "123132AC",
                type = "string"
        )
        @NotEmpty
        String advanced_qualification
) {

}
