package tech.na_app.model.exceptions;

public class ApiException extends CustomExceptionAbs {


    public ApiException(Integer code, String message) {
        super(code, message);
    }

}
