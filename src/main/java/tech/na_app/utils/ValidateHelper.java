package tech.na_app.utils;

import tech.na_app.model.exceptions.ApiException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidateHelper {

    public static void validateInput(Object input) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> violations = validator.validate(input);
            if (!violations.isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
        }
    }

}
