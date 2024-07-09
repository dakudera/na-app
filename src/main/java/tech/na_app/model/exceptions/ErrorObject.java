package tech.na_app.model.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ErrorObject extends CustomExceptionAbs{


    public ErrorObject(Integer code, String description) {
        super(code, description);
    }
}
