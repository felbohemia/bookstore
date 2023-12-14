package com.atamie.mark.bookstore.iserviceimps;

import com.atamie.mark.bookstore.dtos.AuthorDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.entities.Author;
import com.atamie.mark.bookstore.iservices.IAuthorService;
import com.atamie.mark.bookstore.repositories.AuthorRepository;
import com.atamie.mark.bookstore.utils.Messages;
import com.atamie.mark.bookstore.utils.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthorServiceImp implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookOperationResponse addAuthor(AuthorDto dto) {
        BookOperationResponse response = new BookOperationResponse();
        try
        {
            Optional<Author> authorOptional = authorRepository.getAuthorByEmail(dto.getEmail());
            if(authorOptional.isPresent())
            {
                response.setStatus(ResponseCodes._EXIST.getCode());
                response.setMessage(ResponseCodes._EXIST.getMessage());
                response.setResponseDetails(Messages._AUTHOREXIST.getMessage());
                return response;
            }
            Author author = Author.builder()
                    .email(dto.getEmail())
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .nationality(dto.getNationality())
                    .build();
            authorRepository.save(author);
            response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
            response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
            return response;
        }
        catch (Exception exception)
        {
            response.setStatus(ResponseCodes._SERVERERROR.getCode());
            response.setMessage(ResponseCodes._SERVERERROR.getMessage());
            response.setResponseDetails(exception.getLocalizedMessage());
            return response;
        }
    }

    @Override
    public BookOperationResponse getAllAuthors() {
        BookOperationResponse response = new BookOperationResponse();
        try
        {
          response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
          response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
          response.setResponseDetails(authorRepository.findAll());
          return response;
        }
        catch (Exception exception)
        {
            response.setStatus(ResponseCodes._SERVERERROR.getCode());
            response.setMessage(ResponseCodes._SERVERERROR.getMessage());
            response.setResponseDetails(exception.getLocalizedMessage());
            return response;
        }
    }
}
