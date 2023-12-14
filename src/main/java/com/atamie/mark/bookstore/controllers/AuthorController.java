package com.atamie.mark.bookstore.controllers;

import com.atamie.mark.bookstore.dtos.AuthorDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.iservices.IAuthorService;
import com.atamie.mark.bookstore.validations.BookValidatorErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @PostMapping("/add")
    public BookOperationResponse addBook(@Valid @RequestBody AuthorDto dto, Errors errors)
    {
        if(errors.hasErrors()){// ideally, this should be done in a controller advice class
            BookOperationResponse response = new BookOperationResponse();
            response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.setResponseDetails(BookValidatorErrorBuilder.fromBindingErrors(errors));
            return response;
        }
        return authorService.addAuthor(dto);
    }

    @GetMapping("/get-all")
    public BookOperationResponse getAllAuthors()
    {
        return authorService.getAllAuthors();
    }
}
