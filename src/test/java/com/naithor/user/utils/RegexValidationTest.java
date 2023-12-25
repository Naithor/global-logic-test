package com.naithor.user.utils;

import org.junit.jupiter.api.Test;

import static com.naithor.user.services.EmailService.REGEX_EMAIL_PATTERN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegexValidationTest {

    @Test
    void givenValueAndRegexExpression_shouldReturnFalse() {
        String value = "user@email.com";

        boolean result = RegexValidation.regexValidation(value, REGEX_EMAIL_PATTERN);

        assertFalse(result);
    }

    @Test
    void givenValueAndRegexExpression_shouldReturnTrue() {
        String value = "user";

        boolean result = RegexValidation.regexValidation(value, REGEX_EMAIL_PATTERN);

        assertTrue(result);
    }

}
