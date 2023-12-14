package com.atamie.mark.bookstore.entities;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends SupperEntity {

    @Id
    @Column(name = "book_id")
    private String isbn;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "price",precision = 6, scale = 2)
    private Double price;

    @Column(name = "quantity_available")
    private Integer quantity;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
    //private constructor, prevents new Book() from outside this class scope
    public Book(){}

    public static BookBuilder builder()
    {
        return new BookBuilder();
    }

    public static class BookBuilder
    {
        private Book book = new Book();
        public BookBuilder isbn(String isbn)
        {
            book.setIsbn(isbn);
            return this;
        }
        public BookBuilder author(Author author)
        {
            book.setAuthor(author);
            return this;
        }

        public BookBuilder publicationDate(String date)
        {
            book.setPublicationDate(date);
            return this;
        }

        public BookBuilder title(String title)
        {
            book.setTitle(title);
            return this;
        }

        public BookBuilder price(Double price)
        {
            book.setPrice(price);
            return this;
        }
        public BookBuilder quantity(Integer quantity)
        {
            book.setQuantity(quantity);
            return this;
        }
        public Book build()
        {
            return book;
        }
    }

}
