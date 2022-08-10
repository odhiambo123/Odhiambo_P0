package com.davidodhiambo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyConnectionJDBC {
    private static Connection connection = null;


       public static Connection getConnection(){
           if (connection == null){
               ResourceBundle rb = ResourceBundle.getBundle("dbConfig");
               String url = rb.getString("url");
               String user = rb.getString("user");
               String password = rb.getString("password");
               try {
                   connection = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                   throw new RuntimeException("Error connecting to the database", e);
               }
           }
           return connection;
       }
}
