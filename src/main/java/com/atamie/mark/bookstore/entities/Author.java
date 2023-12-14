package com.atamie.mark.bookstore.entities;


import javax.persistence.*;

/*
represents authors of books
uses builder design pattern
 */
@Entity
@Table(name = "books_authors")
public class Author extends SupperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name= "last_name")
    private String lastName;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "email",unique = true,nullable = false)
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getEmail() {
        return email;
    }


    public static AuthorBuilder builder()
    {
        return new AuthorBuilder();
    }

    public static class AuthorBuilder
    {
        private Author author = new Author();

        public AuthorBuilder firstName(String firstName)
        {
            author.setFirstName(firstName);
            return this;
        }
        public AuthorBuilder lastName(String lastName)
        {
            author.setLastName(lastName);
            return this;
        }
        public AuthorBuilder nationality(String nation)
        {
            author.setNationality(nation);
            return this;
        }
        public AuthorBuilder email(String email)
        {
            author.setEmail(email);
            return this;
        }
        public Author build()
        {
            return author;
        }
    }
    public Author(){}

}
