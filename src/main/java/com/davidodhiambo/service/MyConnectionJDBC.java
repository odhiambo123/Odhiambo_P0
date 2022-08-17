package com.davidodhiambo.service;

import com.davidodhiambo.ui.CheckingAccountMenu;
import com.davidodhiambo.ui.EmployeeMenu;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MyConnectionJDBC {
    static Connection connection = null;
    private MyConnectionJDBC() {}

       public static Connection getConnection(){
        //*******************************************************************
        try(InputStream input = new FileInputStream("src/main/java/com/davidodhiambo/service/config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            //String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            //Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return connection;
       }


}
