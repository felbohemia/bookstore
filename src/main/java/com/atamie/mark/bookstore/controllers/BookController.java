package com.atamie.mark.bookstore.controllers;

import com.atamie.mark.bookstore.dtos.BookDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.dtos.UpdateBookDto;
import com.atamie.mark.bookstore.iservices.IBookService;
import com.atamie.mark.bookstore.validations.BookValidatorErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")// ideally, base path should be in the config
public class BookController {
    /*
        dependency injection from the container itself
        so i do not have to create an instance of the
        interface 'IBookService' implementation
     */
    @Autowired
    private IBookService bookService;
    @PostMapping("/add")
    public BookOperationResponse addBook(@Valid @RequestBody BookDto dto, Errors errors)
    {
        if(errors.hasErrors()){// ideally, this should be done in a controller advice class
            BookOperationResponse response = new BookOperationResponse();
            response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.setResponseDetails(BookValidatorErrorBuilder.fromBindingErrors(errors));
            return response;
        }
        return bookService.addBooks(dto);
    }
    @GetMapping("/get-all")
    public BookOperationResponse getAllBooks()
    {
        return bookService.getBooks();
    }

    @GetMapping("/get-author/{authorId}")
    public BookOperationResponse getBooksByAuthorId(@PathVariable("authorId") Long id)
    {
        return bookService.getByAuthorId(id);
    }

    @DeleteMapping("/delete/{isbn}")
    public BookOperationResponse deleteBookByISBN(@PathVariable("isbn") String isbn)
    {
        return bookService.deleteBook(isbn);
    }
    @PatchMapping("/update")
    public BookOperationResponse updateABook(@Valid @RequestBody UpdateBookDto dto, Errors errors)
    {
        if(errors.hasErrors()){// ideally, this should be done in a controller advice class
            BookOperationResponse response = new BookOperationResponse();
            response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.setResponseDetails(BookValidatorErrorBuilder.fromBindingErrors(errors));
            return response;
        }
        return bookService.updateBookDetails(dto);
    }
    @GetMapping("get-isbn/{isbn}")
    public BookOperationResponse getBookByISBN(@PathVariable("isbn") String isbn)
    {
        return bookService.getBookByISBN(isbn);
    }
}
