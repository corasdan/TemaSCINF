package com.company.service;

import com.company.model.Atlas;
import com.company.model.Book;
import com.company.model.Magazine;
import com.company.util.IllegalTypeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> listOfBooks;

    private static Logger logger = LogManager.getLogger(BookService.class);

    public BookService() throws FileNotFoundException{
        logger.log(Level.INFO, "incep populare", listOfBooks);
        populateResource();
        logger.log(Level.INFO, "termin populare",listOfBooks);

    }
    //CRUD


    //C create

    public void populateResource()throws FileNotFoundException{
        //load from file
        logger.log(Level.INFO, "in operatiune de populare");
        int lineCounter = 0;//variable that retains the number of lines in the file
        listOfBooks= new ArrayList<>();
        String currentUserDir = System.getProperty("user.dir") + "\\src\\main\\resources\\test.txt";
        try(FileReader file = new FileReader(currentUserDir);
            BufferedReader objReader = new BufferedReader(file)) {           //try-with-resources implementation
            String strCurrentLine;                                          //A string containing the entire line read from the file
            while ((strCurrentLine = objReader.readLine()) != null) {
                String[] currentLineArray = strCurrentLine.split("//");// A string array in which each indexed element is the AuthorName/NameOfTheBook/etc.
                if (currentLineArray.length > 1) {
                    String BookType = currentLineArray[2];                             //The third element of the array always holds the Book Type
                    switch (BookType) {
                        case "Atlas":
                            Book a = new Atlas();
                            a.setNameOfTheBook(currentLineArray[0]);
                            a.setAuthorName(currentLineArray[1]);
                            Double d1 = 0.0;
                            try {
                                d1 = parseDouble(currentLineArray[3]);
                            } catch (IllegalTypeException e) {
                                System.out.println(e.getMessage());
                            }
                            a.setPriceInEuros(d1);
                            listOfBooks.add(a);
                            logger.log(Level.INFO, "gasit atlas");
                            break;
                        case "Magazine":
                            Book r = new Magazine();
                            r.setNameOfTheBook(currentLineArray[0]);
                            r.setAuthorName(currentLineArray[1]);
                            Double d2 = 0.0;
                            try {
                                d2 = parseDouble(currentLineArray[3]);
                            } catch (IllegalTypeException e) {
                                System.out.println(e.getMessage());
                            }
                            r.setPriceInEuros(d2);
                            listOfBooks.add(r);
                            logger.log(Level.INFO, "gasit magazine");
                            break;
                        default:
                            System.out.println("Nu se incadreaza!!");
                    }
                }
            }
        }catch(IOException e){
            throw new FileNotFoundException();
        }
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
    }

    public void insertInIndex(int index , Book book){
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

    /*
    Parsing double values introduced as a parameter
     */
    public static Double parseDouble(String d)throws IllegalTypeException{
        Double value;
        try{
            value= Double.valueOf(d);
        }catch (NumberFormatException e){
            throw new IllegalTypeException("Value should be of type double");
        }
        return value;
    }
}
