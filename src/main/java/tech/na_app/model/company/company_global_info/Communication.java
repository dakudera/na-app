package tech.na_app.model.company.company_global_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Communication {

    @Schema(
            example = "+38012345678"
    )
    private List<String> phone_number;

    @Schema(
            example = "example@gmail.com"
    )
    private String email;

}
