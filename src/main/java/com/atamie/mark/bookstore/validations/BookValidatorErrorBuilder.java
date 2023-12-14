package com.atamie.mark.bookstore.validations;

import com.atamie.mark.bookstore.entities.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class BookValidatorErrorBuilder {

    public static BookValidator fromBindingErrors(Errors errors)
    {
        BookValidator error = new BookValidator("Validation Failed. "+errors.getErrorCount()+ " Errors");

        for(ObjectError objectError: errors.getAllErrors())
        {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
