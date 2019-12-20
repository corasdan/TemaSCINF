package com.company.Repository;

import com.company.model.Atlas;
import com.company.model.Book;
import com.company.util.DBConnectionService;
import org.apache.logging.log4j.LogManager;

import javax.sound.midi.Soundbank;
import java.sql.*;

public class ModelRepository {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ModelRepository.class);

    private static final String SEP1 = "'";
    private static final String SEP2 = "', ";
    private static final String SEP3 =", ";

    private DBConnectionService conService;

    public ModelRepository() {
        conService = DBConnectionService.getInstance();
    }

    public void insertBook(Book book) throws Exception {
        this.insert("Book", book);
    }

    public void insertAltlas(Book book) throws Exception {
        this.insert("Atlas", book);
    }

    public void insertMagazine(Book book) throws Exception {
        this.insert("Magazine", book);
    }


    public void insert(String tabelName, Book book) throws Exception {
        String querry = "INSERT INTO BOOKS."+ tabelName + "(NAMEOFTHEBOOK, AUTHORNAME, PRICEINEUROS) VALUES (%s);";

        StringBuilder querryParams = new StringBuilder();
        //('ISBN', 'Dama cu camelii', 'Slavici', 266, 245.00);


        querryParams.append(SEP1);
        querryParams.append(book.getNameOfTheBook());
        querryParams.append(SEP2);

        querryParams.append(SEP1);
        querryParams.append(book.getAuthorName());
        querryParams.append(SEP2);


        querryParams.append(book.getPrice());


        querry = String.format(querry, querryParams.toString());

        conService.getStatement().executeUpdate(querry);
    }

//    private static final String SEP1 = "'";
//    private static final String SEP2 = "', ";
//    private static final String SEP3 =", ";

    //updateBookDB- Metoda supraincarcata la metoda updateBook
    public void updateBookDB(Book book) throws Exception {
        this.updateBook("Book", book);
}

    public void updateBook(String tabelName, Book book) throws Exception{
        String querry = "UPDATE BOOKS."+ tabelName + " SET %s";

        StringBuilder querryParams = new StringBuilder();
        /*
        UPDATE BOOKS.BOOK
        SET NAMEOFTHEBOOK = 'ToatePanzele', AUTHORNAME = 'Ioan Slavici', PRICEINEUROS=12.0
        WHERE ID=4;
         */
        querryParams.append("NAMEOFTHEBOOK=");
        querryParams.append(SEP1);
        querryParams.append(book.getNameOfTheBook());
        querryParams.append(SEP2);

        querryParams.append("AUTHORNAME=");
        querryParams.append(SEP1);
        querryParams.append(book.getAuthorName());
        querryParams.append(SEP2);

        querryParams.append("PRICEINEUROS=");
        querryParams.append(book.getPrice());

        querryParams.append(" WHERE ID=4");

        querry = String.format(querry, querryParams.toString());

        conService.getStatement().executeUpdate(querry);
    }

    /*
        Returneaza o carte din baza de date
        Returneaza unul sau mai multe campuri din baza de date

     */
    public void getBook(String tabelName, String column, String condition) throws Exception{
        String querry = "";
        String querry1 = "";


        String str1= String.format("SELECT %s FROM BOOKS.",column);
        String str2=String.format("%s ",tabelName);
        String str3=String.format("WHERE %s",condition);
        querry1 = str1.concat(str2);
        querry=querry1.concat(str3);

        conService.getStatement().executeQuery(querry);
//        ResultSet rs = conService.getStatement().executeQuery(querry);
//        System.out.println(rs.getString("NAMEOFTHEBOOK")); !!!Intreaba despre result set
    }

    public void deleteBook(String tabelName, String condition) throws Exception{
        String querry = "DELETE FROM BOOKS."+ tabelName + " WHERE %s";

        StringBuilder querryParams = new StringBuilder();
        /*
        DELETE FROM table_name WHERE condition;
         */
        querryParams.append(condition);

        querry = String.format(querry, querryParams.toString());

        conService.getStatement().executeUpdate(querry);
    }
}
