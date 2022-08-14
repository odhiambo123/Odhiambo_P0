package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDaoFactory;
import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.service.MyConnectionJDBC;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void border() {
        System.out.println("==================================\n==================================");
    }
    public static void animate(){
        for (int i = 0; i < 3; i++) {
            System.out.print("--->");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws SQLException, InterruptedException {
        //read from properties file
        border();
        //dao
        BankDao dao = BankDaoFactory.getIEmployeeDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to your Bank of the People");
        border();
        boolean flag = true;
        while (flag) {
            System.out.println("Are you a new customer, an existing customer or employee?");
            System.out.println("1. New Checking Account");
            System.out.println("2. New Savings Account");
            System.out.println("3. Login to an existing Checking account");
            System.out.println("4. Login to an existing Savings account");
            System.out.println("5. Login as an Employee");
            System.out.println("6. Exit");

          border();
            System.out.println("Please enter your choice : \n");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter first name : ");
                    String fname = sc.nextLine();
                    System.out.println("Enter last name : ");
                    String lname = sc.nextLine();
                    System.out.println("Enter email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter password : ");
                    String password = sc.nextLine();
                    System.out.println("Enter Deposit Amount : ");
                    border();
                    double deposit = Double.parseDouble(sc.nextLine());
                    animate();
                    MyConnectionJDBC.add_new_checking_account(fname, lname, email, password, deposit, 0.0);

                }
                case 2 -> {
                    System.out.println("Enter first name : ");
                    String fname = sc.nextLine();
                    System.out.println("Enter last name : ");
                    String lname = sc.nextLine();
                    System.out.println("Enter email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter password : ");
                    String password = sc.nextLine();
                    System.out.println("Enter Deposit Amount : ");
                    border();
                    double deposit = Double.parseDouble(sc.nextLine());
                    animate();
                    MyConnectionJDBC.add_new_savings_account(fname, lname, email, password, deposit, 0.0);
                }
                case 3 -> {
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    border();
                    animate();
                    //check if account exists
                    if (MyConnectionJDBC.login_checking_account(email, password)) {
                        System.out.println("Login Successful");
                        CheckingAccountMenu.myOptions();
                    } else {
                        animate();
                        System.out.println("Login Failed Please Try Again");
                        animate();
                        border();
                        Thread.sleep(1000);
                    }
                }
                case 4 -> {
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    border();
                    animate();
                    if (MyConnectionJDBC.login_savings_account(email, password)) {
                        System.out.println("Login Successful");
                        SavingsAccountMenu.myOptions();
                    } else {
                        animate();
                        System.out.println("Login Failed Please Try Again");
                        animate();
                        border();
                        Thread.sleep(1000);
                    }
                }

                case 5 -> {
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    border();
                    animate();
                    if (MyConnectionJDBC.checkem_if_employee_exists(email, password)) {
                        System.out.println("Login Successful");
                        EmployeeMenu.displayMenu();
                    } else {
                        animate();
                        System.out.println("Login Failed Please Try Again");
                        animate();
                        border();
                        Thread.sleep(1000);
                    }
                }

            }
        }

    }
}
