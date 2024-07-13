package tech.na_app.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {

    private int code;
    private String description;

    public ErrorObject(int code) {
        this.code = code;
    }
}
