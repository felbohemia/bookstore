package com.atamie.mark.bookstore.iservices;

import com.atamie.mark.bookstore.dtos.AuthorDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;

public interface IAuthorService {
    BookOperationResponse addAuthor(AuthorDto dto);
    BookOperationResponse getAllAuthors();
}
