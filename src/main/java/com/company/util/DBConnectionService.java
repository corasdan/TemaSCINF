package com.company.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class DBConnectionService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(DBConnectionService.class);
    private static DBConnectionService dbConnectionService;
    // JDBC driver name and database URL
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private Statement stmt = null;
    private PreparedStatement pStmt = null;

    private Connection conn = null;

    //  Database credentials
    private final String USER;
    private final String PASS;

    private DBConnectionService(){
        PropertiesFileReaderService propsService = null;
        try {
            propsService = new PropertiesFileReaderService();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Properties file not found.");
            new Error();
        }

        JDBC_DRIVER = propsService.getDriver();
        DB_URL = propsService.getConnector();
        USER = propsService.getUserName();
        PASS = propsService.getPassword();


        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            logger.log(Level.INFO,("Connecting to database..."));
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            logger.log(Level.ERROR, "DB connection failed", e);
        }
    }

    public static DBConnectionService getInstance(){
        if(dbConnectionService == null) {
            dbConnectionService = new DBConnectionService();
            return dbConnectionService;
        }
        else {
            return dbConnectionService;
        }
    }

    public Statement getStatement(){
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Cannot create statement!",  e);
            new Error();
        }
        return stmt;
    }
}
