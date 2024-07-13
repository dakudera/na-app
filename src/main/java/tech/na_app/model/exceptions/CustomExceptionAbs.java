package tech.na_app.model.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
public abstract class CustomExceptionAbs extends RuntimeException {

    @Schema(
            example = "0"
    )
    private Integer code;
    @Schema(
            example = "null"
    )
    private String message;

    public CustomExceptionAbs(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
