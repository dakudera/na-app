package tech.na_app.entity.profile;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {


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
