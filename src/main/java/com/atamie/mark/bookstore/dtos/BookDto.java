package com.atamie.mark.bookstore.dtos;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class BookDto {

    @NotNull
    @NotBlank
    private String isbn;

    @NotBlank
    @NotNull
    private String title;

    @Min(1)
    private Long authorId;

    @NotNull
    @NotBlank
    private String publicationDate;

    @Digits(integer = 6, fraction = 2, message = "Maximum of 6 digits in total, with 2 decimal places")
    private Double price;

    private Integer quantity;

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

}
