package com.atamie.mark.bookstore.iservices;

import com.atamie.mark.bookstore.dtos.BookDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.dtos.UpdateBookDto;
import com.atamie.mark.bookstore.entities.Book;

import java.util.List;

public interface IBookService {
    BookOperationResponse getBooks();
    BookOperationResponse getByTitle(String title);
    BookOperationResponse getByAuthorId(Long authorId);
    BookOperationResponse addBooks(BookDto bookDto);
    BookOperationResponse updateBookDetails(UpdateBookDto bookDto);
    BookOperationResponse deleteBook(String isbn);
    BookOperationResponse getBookByISBN(String isbn);
}
