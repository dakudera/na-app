package tech.na_app.model.user.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {

    @Schema(
            example = "1"
    )
    private String id;

    @Schema(
            example = "Vasil"
    )
    private String fio;

    @Schema(
            example = "Водій"
    )
    private String role;

}
