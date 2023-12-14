package com.atamie.mark.bookstore.repositories;

import com.atamie.mark.bookstore.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book , String> {

    @Query(value = "select * from books m where m.title like '%?1%'", nativeQuery = true)
    List<Book> getAllBaseOnTitle(String title);

    @Query(value = "select * from books m where m.author_id = ?1 ", nativeQuery = true)
    List<Book> getAllBaseOnAuthorId(Long authorId);

}
