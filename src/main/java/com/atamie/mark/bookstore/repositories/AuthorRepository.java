package com.atamie.mark.bookstore.repositories;

import com.atamie.mark.bookstore.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author,Long> {

    @Query(value = "select * from books_authors where email = ?1", nativeQuery = true)
    Optional<Author> getAuthorByEmail(String email);
}
