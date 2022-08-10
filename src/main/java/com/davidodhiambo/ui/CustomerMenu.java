package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankModel;
import com.davidodhiambo.data.CustomerModel;
import com.davidodhiambo.service.Login;

public class CustomerMenu {
    CustomerModel cm = new CustomerModel();
    BankModel bm = new BankModel();

    public void displayMenu() {
        System.out.println("Welcome to the Bank of the People");
        System.out.println("please enter your username and password");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                System.out.println("Please enter your username");
                String username = System.console().readLine();
                System.out.println("Please Enter your passworld");
                String password = System.console().readLine();
                Login.loginCustomer(username, password);
                break;
        }
    }
}