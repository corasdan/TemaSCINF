package com.company.service;

import com.company.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        System.out.println("trying to run setup");
        try {
            bookService = new BookService();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        System.out.println("trying to clean up");
    }


    @Test
    void addBook() {
        System.out.println("trying to run addBook");

        Book book = new Book();
        book.setNameOfTheBook("jsfbvsjkbv");

        bookService.addBook(book);

        assertEquals(5,bookService.getNrOfBooks());


    }

    @Test
    void getBookByIndex() {
        System.out.println("trying to run getBookByIndex");

        Book book = new Book();
        book.setNameOfTheBook("jsfbvsjkbv");
        book.setAuthorName("wefwefwefwefwefwefwf");

        bookService.addBook(book);
        int a=bookService.getNrOfBooks()-1;
        assertEquals(bookService.getBookByIndex(a),book);

        bookService.insertInIndex(0, book);

        assertEquals(bookService.getBookByIndex(0),book);

    }

    @Test
    void getBookByName() {
        System.out.println("trying to run getBookByName");
        Book book = new Book();
        book.setNameOfTheBook("Vlad");
        book.setPriceInEuros(200);
        bookService.addBook(book);
        bookService.getBookByName("Vlad");
        assertEquals(200,bookService.getBookByName("Vlad").getPrice());
    }

    @Test
    void updateBook() {
        System.out.println("trying to run updateBook");
        Book book = new Book();
        book.setNameOfTheBook("Radu");
        book.setPriceInEuros(100);
        bookService.updateBook(bookService.getBookByName("Fashion Magazine"),book);
        assertEquals(100,bookService.getBookByName("Radu").getPrice());
    }

    @Test
    void deleteBook() {
        System.out.println("trying to run deleteBook");
        int a= bookService.getNrOfBooks();
        bookService.deleteBook(bookService.getBookByName("Fashion Magazine"));
        assertEquals(a-1,bookService.getNrOfBooks());
    }
}