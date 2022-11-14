package tech.na_app.model;

import lombok.Data;

@Data
public class ApiException extends Exception {

    private Integer code;
    private String message;

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
