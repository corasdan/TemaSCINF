package com.company.service;

import com.company.model.Atlas;
import com.company.model.Book;
import com.company.model.Magazine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> listOfBooks;
    public BookService() throws FileNotFoundException{
        populateResource();
    }
    //CRUD

    //C create

    public void populateResource()throws FileNotFoundException{
        //load from file
        int lineCounter = 0;//variable that retains the number of lines in the file
        listOfBooks= new ArrayList<>();
        String currentUserDir = System.getProperty("user.dir") + "\\test.txt";
        try {
            FileReader file = new FileReader(currentUserDir);
            BufferedReader objReader = new BufferedReader(file);
            String strCurrentLine;
            while ((strCurrentLine = objReader.readLine()) != null) {
                String[] array = strCurrentLine.split("//");//array in which each indexed element is the AuthorName/NameOfTheBook/etc.
                if (array.length > 1) {
                    switch (array[2]) {
                        case "Atlas":
                            Book a = new Atlas();
                            a.setNameOfTheBook(array[0]);
                            a.setAuthorName(array[1]);
                            Double d1 = Double.valueOf(array[3]);
                            a.setPriceInEuros(d1);
                            listOfBooks.add(a);
                            lineCounter++;
                            break;
                        case "Magazine":
                            Book r = new Magazine();
                            r.setNameOfTheBook(array[0]);
                            r.setAuthorName(array[1]);
                            Double d2 = Double.valueOf(array[3]);
                            r.setPriceInEuros(d2);
                            listOfBooks.add(r);
                            lineCounter++;
                            break;
                        default:
                            System.out.println("Nu se incadreaza!!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
    }

    public void insetInIndex(int index ,Book book){
        listOfBooks.add(index, book);
    }

    //R read

    public Book getBookByIndex(int index){
        return listOfBooks.get(index);
    }

    public Book getBookByName(String bookName){
        for(Book book : listOfBooks){
            if(book.getNameOfTheBook().equals(bookName)){
                return book;
            }
        }
        return null;
    }

    //U update

    public void updateBook(Book oldBook, Book updatedBook){
        Book foundBook = getBookByName(oldBook.getNameOfTheBook());
        //  metoda1
        listOfBooks.set(listOfBooks.indexOf(foundBook), updatedBook);
        //metoda2
//        foundBook.setAuthorName(updatedBook.getAuthorName());
//        foundBook.setIsbn(updatedBook.getIsbn());
//        foundBook.setNameOfTheBook(updatedBook.getNameOfTheBook());
//        foundBook.setPageNumber(updatedBook.getPageNumber());
//        foundBook.setPriceInEuros(updatedBook.getPrice());

    }


    public void deleteBook(Book book){
        listOfBooks.remove(getBookByName(book.getNameOfTheBook()));
    }

    public int getNrOfBooks(){
        return listOfBooks.size();
    }
}
