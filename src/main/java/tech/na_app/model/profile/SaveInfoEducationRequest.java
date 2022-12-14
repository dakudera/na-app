package tech.na_app.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveInfoEducationRequest {


    private Integer userId;
    @Schema(
            example = "12312313",
            type = "string"
    )
    private String certificate;

    @Schema(
            example = "Mechanic",
            type = "string"
    )
    private String specialty;

    @Schema(
            example = "123132AC",
            type = "string"
    )
    private String advanced_qualification;

}
