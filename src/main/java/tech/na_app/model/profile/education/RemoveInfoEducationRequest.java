package tech.na_app.model.profile.education;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public record RemoveInfoEducationRequest(
        @Schema(
                example = "1"
        )
        @NotEmpty
        String id,
        @NotEmpty
        String userId
) {

}
