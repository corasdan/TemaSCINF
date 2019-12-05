package com.company.service;

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
            assertEquals(3, bookService.getNrOfBooks());
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

        assertEquals(4,bookService.getNrOfBooks());


    }

    @Test
    void getBookByIndex() {
        System.out.println("trying to run getBookByIndex");

        Book book = new Book();
        book.setNameOfTheBook("jsfbvsjkbv");
        book.setAuthorName("wefwefwefwefwefwefwf");

        bookService.addBook(book);

        assertEquals(bookService.getBookByIndex(3),book);

        bookService.insetInIndex(0, book);

        assertEquals(bookService.getBookByIndex(0),book);

    }

    @Test
    void getBookByName() {
        System.out.println("trying to run getBookByName");
    }

    @Test
    void updateBook() {
        System.out.println("trying to run updateBook");
    }

    @Test
    void deleteBook() {
        System.out.println("trying to run deleteBook");
    }
}