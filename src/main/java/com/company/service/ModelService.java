package com.company.service;

import com.company.Repository.ModelRepository;
import com.company.model.Book;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class ModelService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ModelService.class);
    private ModelRepository modelRepository;
    private List<Book> booksCache = new ArrayList<>();

    public ModelService(){
        modelRepository = new ModelRepository();
    }

    public boolean insertBookInInventory(Book book){
        boolean success = true;
        booksCache.add(book);

        try {
            modelRepository.insertBook(book);
        } catch (Exception e) {
            logger.log(Level.ERROR, "there was an error calling insertBookInInventory");
            booksCache.remove(book);
            success =false;
        }
        return success;
    }

    public void insertBookBatchInInventory(List<Book> bookList){

        for(Book book : bookList)
        {
            this.insertBookInInventory(book);
        }
    }

    public boolean updateBookInInventory(Book book){
        boolean success = true;
        booksCache.add(book);

        try {
            modelRepository.updateBookDB(book);
        } catch (Exception e) {
            logger.log(Level.ERROR, "there was an error calling updateBookInInventory");
            booksCache.remove(book);
            success =false;
        }
        return success;
    }

    public boolean getBookInInventory(String tabelName, String column, String condition){
        boolean success = true;

        try {
            modelRepository.getBook(tabelName,column,condition);
        } catch (Exception e) {
            logger.log(Level.ERROR, "there was an error calling getBookInInventory");
            success =false;
        }
        return success;
    }

    public boolean deleteBookInInventory(String tabelName, String condition){
        boolean success = true;

        try {
            modelRepository.deleteBook(tabelName,condition);
        } catch (Exception e) {
            logger.log(Level.ERROR, "there was an error calling deleteBookInInventory");
            success =false;
        }
        return success;
    }
}
