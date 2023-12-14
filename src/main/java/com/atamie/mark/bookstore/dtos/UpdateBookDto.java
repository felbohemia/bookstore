package com.atamie.mark.bookstore.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateBookDto {
    /*
    only isbn is required
     */
    @NotNull
    @NotBlank
    private String isbn;

    private String title;

    private Long authorId;

    private String publicationDate;

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
