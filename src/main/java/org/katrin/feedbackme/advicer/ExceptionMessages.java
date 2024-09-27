package org.katrin.feedbackme.advicer;

import lombok.Getter;

@Getter
public enum ExceptionMessages {
    NOT_FOUND("Not found."),
    INVALID_INPUT("Invalid input."),
    ERROR_OCCURRED("Error occurred.");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
