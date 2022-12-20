package tech.na_app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterValidatorTest {

    @Test
    public void emailIsValid() {
        assertFalse(ParameterValidator.emailIsValid("12312"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@test"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@testcom"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@gmailcom"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@gmail.com_"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@_gmail.com"));
        assertFalse(ParameterValidator.emailIsValid(" test_email12312@gmail.com"));
        assertFalse(ParameterValidator.emailIsValid("test_email12312@gmail.com "));
        assertFalse(ParameterValidator.emailIsValid("testEmail12312@gmail.com"));
        assertTrue(ParameterValidator.emailIsValid("test_email12312@gmail.com"));
        assertTrue(ParameterValidator.emailIsValid("test.email@gmail.com"));
        assertTrue(ParameterValidator.emailIsValid("test.email.123@gmail.com"));
    }
}