package com.atamie.mark.bookstore.validations;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class BookValidator {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();
    private final String errorMessage;
    public BookValidator(String error)
    {
        this.errorMessage = error;
    }
    public void addValidationError(String error)
    {
        errors.add(error);
    }
    public List<String> getErrors()
    {
        return errors;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
}
