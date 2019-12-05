package com.company.model;

import java.util.Objects;

public class Book {
    private double priceInEuros;
    private String isbn;
    private String nameOfTheBook;
    private int pageNumber;
    private String authorName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getIsbn().equals(book.getIsbn()) &&
                getNameOfTheBook().equals(book.getNameOfTheBook()) &&
                getAuthorName().equals(book.getAuthorName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getNameOfTheBook(), getAuthorName());
    }

    public double getPrice() {
        return priceInEuros;
    }

    public void setPriceInEuros(double priceInEuros) {
        this.priceInEuros = priceInEuros;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNameOfTheBook() {
        return nameOfTheBook;
    }

    public void setNameOfTheBook(String nameOfTheBook) {
        this.nameOfTheBook = nameOfTheBook;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
