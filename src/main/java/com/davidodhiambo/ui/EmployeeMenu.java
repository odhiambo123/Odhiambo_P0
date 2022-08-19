package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.data.BankDaoFactory;
import com.davidodhiambo.service.MyConnectionJDBC;

import java.sql.SQLException;
import java.util.Scanner;

import static com.davidodhiambo.ui.Main.animate;
import static com.davidodhiambo.ui.Main.border;

public class EmployeeMenu {
    static Scanner sc = new Scanner(System.in);
    static BankDao dao = BankDaoFactory.getAccounts();
    public static void displayMenu(String email1) throws SQLException {
        border();
        System.out.println("Welcome Back!");
        border();
        System.out.println("Employee Menu");
        System.out.println("1. Create Account");
        System.out.println("2. Update Account");
        System.out.println("3. Delete Account");
        System.out.println("4. List Employees");
        System.out.println("5. Approve Account");
        System.out.println("6. Reject Account");
        System.out.println("7. Eixt");
        System.out.println("8. View Logs");
        System.out.println("Enter your choice: ");
        int choice3 = Integer.parseInt(sc.nextLine());
        switch (choice3) {
            case 1 -> {
//create account
                System.out.println("what type of account do you want to create?");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Back");
                int choice4 = Integer.parseInt(sc.nextLine());
                switch (choice4) {
                    case 1 -> {
                        //checking account
                        System.out.println("enter first name: ");
                        String fname = sc.nextLine();
                        System.out.println("enter last name: ");
                        String lname = sc.nextLine();
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("Enter amount for initial deposit: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.add_new_checking_account(fname, lname, email, password, amount, 0.0);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 2 -> {
                        //savings account
                        System.out.println("enter first name: ");
                        String fname = sc.nextLine();
                        System.out.println("enter last name: ");
                        String lname = sc.nextLine();
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("Enter amount for initial deposit: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.add_new_savings_account(fname, lname, email, password, amount, 0.0);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 3 -> {//back
                        displayMenu(email1);
                    }

                }
            }

            case 2 -> {
//update account
                System.out.println("what type of account do you want to update?");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Back");
                int choice5 = Integer.parseInt(sc.nextLine());
                switch (choice5) {
                    case 1 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("update email: ");
                        String new_email = sc.nextLine();
                        System.out.println("update password: ");
                        String new_password = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.update_checking_account_email_and_password(email, password, new_email, new_password);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 2 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("update email: ");
                        String new_email = sc.nextLine();
                        System.out.println("update password: ");
                        String new_password = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.update_savings_account_email_and_password(email, password, new_email, new_password);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 3 -> {
                        displayMenu(email1);
                    }
                }
            }
            case 3 -> {
//delete account
                System.out.println("what type of account do you want to delete?");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Back");
                int choice6 = Integer.parseInt(sc.nextLine());
                switch (choice6) {
                    case 1 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.delete_checking_account(email, password);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 2 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("enter password: ");
                        String password = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.delete_savings_account(email, password);
                        border();
                        System.out.println("Account pending approval...");
                        border();
                        displayMenu(email1);
                    }
                    case 3 -> {
                        displayMenu(email1);
                    }
                }
            }

            case 4 -> {
//list employees
                System.out.println("List of employees: ");
                dao.list_All_Employees();
                displayMenu(email1);
            }


            case 5 -> {
//approve accounts
                System.out.println("Account Type: ");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Back");
                int choice7 = Integer.parseInt(sc.nextLine());
                switch (choice7) {
                    case 1 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("Thank you... please wait...\n ");
                        animate();
                        dao.approve_checking_account(email);
                        displayMenu(email1);
                    }
                    case 2 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.approve_savings_account(email);
                        border();
                        displayMenu(email1);
                    }
                    case 3 -> {
                        displayMenu(email1);
                    }
                }

            }
            case 6 -> {
                //reject accounts
                System.out.println("Account Type: ");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Back");
                int choice8 = Integer.parseInt(sc.nextLine());
                switch (choice8) {
                    case 1 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.reject_checking_account(email);
                        border();
                        displayMenu(email1);
                    }
                    case 2 -> {
                        System.out.println("enter email: ");
                        String email = sc.nextLine();
                        System.out.println("Thank you... please wait...");
                        animate();
                        dao.reject_savings_account(email);
                        border();
                        displayMenu(email1);
                    }
                    case 3 -> {
                        displayMenu(email1);
                    }
                }
            }
            case 7 -> {
                System.exit(0);
            }

            case 8 -> {
                //view database logs
                System.out.println( "Database Logs: ");
                dao.view_database_logs();


            }

            default -> {
                System.out.println("Invalid choice");
                displayMenu(email1);
            }

        }
    }
}

