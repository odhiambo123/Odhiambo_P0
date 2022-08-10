package com.davidodhiambo.ui;

public class MainMenu {
    void displayMenu() {
        System.out.println("Welcome to the Bank of David");
        System.out.println("Please select an option from the menu below");
        System.out.println("1. Employee");
        System.out.println("2. Customer");
        System.out.println("3. Exit");

        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                EmployeeMenu em = new EmployeeMenu();
                em.displayMenu();
                break;
            case 2:
                CustomerMenu cm = new CustomerMenu();
                cm.displayMenu();
                break;
            case 3:
                System.out.println("Thank you for banking with us");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }


    }

}