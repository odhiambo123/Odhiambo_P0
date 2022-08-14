package com.davidodhiambo.ui;

import com.davidodhiambo.service.MyConnectionJDBC;

import java.sql.SQLException;

public class EmployeeMenu {
    public static void displayMenu() throws SQLException {
        System.out.println("Welcome Back!");
        System.out.println("Employee Menu");
        System.out.println("1. Create Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. List Employees");
        System.out.println("5. Approve Customer");
        System.out.println("6. Reject Customer");
        System.out.println("7. Eixt");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {


        }
    }
}

