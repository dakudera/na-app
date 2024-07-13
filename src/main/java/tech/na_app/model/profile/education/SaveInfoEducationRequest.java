package tech.na_app.model.profile.education;

import io.swagger.v3.oas.annotations.media.Schema;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record   SaveInfoEducationRequest(
        @NotNull
        ObjectId userId,
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
