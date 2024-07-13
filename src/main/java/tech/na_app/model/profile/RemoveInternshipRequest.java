package tech.na_app.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RemoveInternshipRequest {

    private String id;
    @Schema(
            example = "1",
            type = "integer"
    )
    private String userId;

}
