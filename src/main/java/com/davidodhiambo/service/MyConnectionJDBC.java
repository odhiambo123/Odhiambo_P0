package com.davidodhiambo.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyConnectionJDBC {
    private static Connection connection = null;
    private MyConnectionJDBC() {}

       public static Connection getConnection(){
        //*******************************************************************
        try(InputStream input = new FileInputStream("src/main/java/com/davidodhiambo/service/config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            //Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return connection;
            //*******************************************************************
           /*if (connection == null){
               ResourceBundle rb = ResourceBundle.getBundle("Login");
               String url = rb.getString("url");
               String user = rb.getString("user");
               String password = rb.getString("password");

               try {
                   connection = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                   throw new RuntimeException("Error connecting to the database", e);
               }
           }
           return connection;*/
           //*********************************************************************
       }
}
