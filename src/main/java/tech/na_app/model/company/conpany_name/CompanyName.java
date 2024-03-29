package tech.na_app.model.company.conpany_name;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyName {

    @Schema(example = "some name")
    @NotEmpty
    private String full_name;

    @Schema(example = "some name")
    @NotEmpty
    private String short_name;

}
