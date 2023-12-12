package tech.na_app.model.company.company_global_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Communication {

    @Schema(
            example = "+38012345678"
    )
    @NotNull
    private List<String> phone_number;

    @Schema(
            example = "example@gmail.com"
    )
    @NotEmpty
    @Email
    private String email;

}
