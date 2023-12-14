package com.atamie.mark.bookstore.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class AuthorValidatorErrorBuilder {

    public static AuthorValidator fromBindingErrors(Errors errors)
    {
        AuthorValidator error = new AuthorValidator("Validation Failed. "+errors.getErrorCount()+ " Errors");

        for(ObjectError objectError: errors.getAllErrors())
        {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
