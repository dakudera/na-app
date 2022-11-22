package tech.na_app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {

    @Schema(
            example = "0"
    )
    private Integer code;
    @Schema(
            example = "null"
    )
    private String description;

    public ErrorObject(Integer code) {
        this.code = code;
    }
}
