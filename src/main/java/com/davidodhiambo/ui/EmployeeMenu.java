package com.davidodhiambo.ui;

public class EmployeeMenu {
    public static void displayMenu() {
        System.out.println("Employee Menu");
        System.out.println("1. Create Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. List Employees");
        System.out.println("5. Login");
        System.out.println("6. Exit");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                System.out.println("Create Employee");
                break;
            case 2:
                System.out.println("Update Employee");
                break;
            case 3:
                System.out.println("Delete Employee");
                break;
            case 4:
                System.out.println("List Employees");
                break;
            case 5:
                System.out.println("Login");
                break;
            case 6:
                System.out.println("Thank you for banking with us");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}

