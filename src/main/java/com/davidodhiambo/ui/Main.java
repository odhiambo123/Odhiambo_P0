package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDaoFactory;
import com.davidodhiambo.data.BankDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        //read from properties file
        //dao
        BankDao dao = BankDaoFactory.getIEmployeeDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to your Bank of the People");
        boolean flag = true;
        while (flag) {
            System.out.println("Are you a new customer, an existing customer or employee?");
            System.out.println("1. New Customer");
            System.out.println("2. Existing Customer");
            System.out.println("3. Employee");
            System.out.println("4. Exit");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    NewCustomerMenu newCustomerMenu = new NewCustomerMenu();
                    newCustomerMenu.newCustomerMenu();
                }
                case 2 -> {
                    CustomerMenu customerMenu = new CustomerMenu();
                }
                case 3 -> {
                    EmployeeMenu employeeMenu = new EmployeeMenu();
                }
                case 4 -> {
                    flag = false;
                }
                default -> {
                    System.out.println("Invalid choice");
                }
            }
        }

    }
}
