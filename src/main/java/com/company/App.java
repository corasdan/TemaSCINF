package com.company;

import com.company.service.BookService;
import com.company.util.MONTH;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args){
        BookService books;
        try {
            books = new BookService();
            books.populateResource();
            System.out.println(books.getNrOfBooks());
            System.out.println(books.getBookByName("Fashion Magazine").getPrice());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
