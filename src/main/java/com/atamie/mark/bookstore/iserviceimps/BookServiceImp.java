package com.atamie.mark.bookstore.iserviceimps;

import com.atamie.mark.bookstore.dtos.BookDto;
import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.dtos.UpdateBookDto;
import com.atamie.mark.bookstore.entities.Book;
import com.atamie.mark.bookstore.iservices.IBookService;
import com.atamie.mark.bookstore.repositories.AuthorRepository;
import com.atamie.mark.bookstore.repositories.BookRepository;
import com.atamie.mark.bookstore.utils.Messages;
import com.atamie.mark.bookstore.utils.ResponseCodes;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class BookServiceImp implements IBookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Override
    public BookOperationResponse getBooks() {
        BookOperationResponse response = new BookOperationResponse();
        response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
        response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
        try{
            response.setResponseDetails(bookRepository.findAll());
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
    public BookOperationResponse getByTitle(String title) {
        BookOperationResponse response = new BookOperationResponse();
        try
        {
            List<Book>booksHavingSameTitle = bookRepository.getAllBaseOnTitle(title.toLowerCase(Locale.ROOT));
            if(booksHavingSameTitle == null)//title does not exist
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._NOFFOUND.getMessage());
                return response;
            }
            // control reaches here means the title exist
            response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
            response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
            response.setResponseDetails(booksHavingSameTitle);
            return response;
        }
        catch(Exception exception)
        {
            response.setStatus(ResponseCodes._SERVERERROR.getCode());
            response.setMessage(ResponseCodes._SERVERERROR.getMessage());
            response.setResponseDetails(exception.getLocalizedMessage());
            return response;
        }
    }

    @Override
    public BookOperationResponse getByAuthorId(Long authorId) {
        BookOperationResponse response = new BookOperationResponse();
        try{
            if(authorRepository.findById(authorId).isEmpty())
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._NOFFOUND.getMessage());
                return response;
            }
            List<Book> aGivenAuthorBooks = bookRepository.getAllBaseOnAuthorId(authorId);
            response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
            response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
            response.setResponseDetails(aGivenAuthorBooks);
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
    public BookOperationResponse addBooks(BookDto bookDto) {
        BookOperationResponse response = new BookOperationResponse();

        try{
            if(authorRepository.findById(bookDto.getAuthorId()).isEmpty())
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._NOFFOUND.getMessage());
                response.setResponseDetails(Messages._NOTFOUND.getMessage());
                return response;
            }

            Book book = Book.builder()
                    .author(authorRepository.findById(bookDto.getAuthorId()).get())
                    .isbn(bookDto.getIsbn())
                    .price(bookDto.getPrice())
                    .publicationDate(bookDto.getPublicationDate())
                    .quantity(bookDto.getQuantity())
                    .title(bookDto.getTitle())
                    .build();
            bookRepository.save(book);
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
    public BookOperationResponse updateBookDetails(UpdateBookDto bookDto) {
        BookOperationResponse response = new BookOperationResponse();
        try{

            Optional<Book> bookOptional = bookRepository.findById(bookDto.getIsbn());

            if(!bookOptional.isPresent())
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
                response.setResponseDetails(Messages._BOOKISBNNOTFOUND.getMessage());
                return response;
            }
            Book book = bookOptional.get();
            book.setPrice(bookDto.getPrice());
            book.setTitle(bookDto.getTitle());
            int quantity = book.getQuantity();
            /*
            when books are sold out, quantity sold should be negative (less than zero(0) < 0)
            when books are added into the bookshop,quantity should be positive(greater than zero(0) > 0)
             */
            int quantum = bookDto.getQuantity();
            if(quantum < 0)
            {
                book.setQuantity(quantity + (quantum));
            }else
            {
                book.setQuantity(quantity + bookDto.getQuantity());
            }
            bookRepository.save(book);//updates since book is already part of the persistent unit
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
    public BookOperationResponse deleteBook(String isbn) {
        BookOperationResponse response = new BookOperationResponse();
        try
        {
            Optional<Book> bookOptional = bookRepository.findById(isbn);
            if(bookOptional.isEmpty())
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
                response.setResponseDetails(Messages._BOOKISBNNOTFOUND.getMessage());
                return response;
            }
            bookRepository.delete(bookOptional.get());
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
    public BookOperationResponse getBookByISBN(String isbn) {
        BookOperationResponse response = new BookOperationResponse();
        try
        {
            Optional<Book> bookOptional = bookRepository.findById(isbn);
            if(bookOptional.isEmpty())
            {
                response.setStatus(ResponseCodes._NOFFOUND.getCode());
                response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
                response.setResponseDetails(Messages._BOOKISBNNOTFOUND.getMessage());
                return response;
            }
            response.setStatus(ResponseCodes._SUCCESSFUL.getCode());
            response.setMessage(ResponseCodes._SUCCESSFUL.getMessage());
            response.setResponseDetails(bookOptional.get());
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
