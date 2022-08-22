package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.data.BankDaoFactory;

import java.sql.SQLException;
import java.util.Scanner;

import static com.davidodhiambo.ui.BannerAndBorder.*;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws SQLException {

        BankDao dao = BankDaoFactory.getAccounts();
        Scanner sc = new Scanner(System.in);
        banner();

        System.out.println("~~~~~~~~~WELCOME~~~~~~~~~~~~~ \n");

        boolean flag = true;
        while (flag) {
            System.out.println("|| New Customer || Returning Customer || Employee ||");
            System.out.println("1. Open Checking Account");
            System.out.println("2. Open Savings Account");
            System.out.println("3. Login");
            //System.out.println("4. Login to an existing Savings account");
            System.out.println("4. Login as an Employee");
            System.out.println("5. Exit \n");

          border();

            System.out.println("Please enter your choice : \n");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter first name : "); String fname = sc.nextLine();

                    System.out.println("Enter last name : ");  String lname = sc.nextLine();

                    System.out.println("Enter email : ");      String email = sc.nextLine();

                    System.out.println("Enter password : ");   String password = sc.nextLine();

                    System.out.println("Enter Deposit Amount : "); double deposit = Double.parseDouble(sc.nextLine());

                    if (deposit < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }

                    dao.add_new_checking_account(fname, lname, email, password, deposit, 0.0);
                    border();
                    System.out.println("Thank you Account pending approval...");
                    border();

                }
                case 2 -> {
                    System.out.println("Enter Account owner first name : ");   String fname = sc.nextLine();
                    System.out.println("Last name : ");                        String lname = sc.nextLine();
                    System.out.println("Email : ");                            String email = sc.nextLine();
                    System.out.println("Password : ");                         String password = sc.nextLine();
                    System.out.println("Initial Deposit : "); border(); double deposit = Double.parseDouble(sc.nextLine());

                    if (deposit < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }

                    animate();
                    dao.add_new_savings_account(fname, lname, email, password, deposit, 0.0);
                    border();
                    System.out.println("\nThank you Account pending approval...");
                    border();


                }
                case 3 -> {
                    System.out.println("Enter your email : ");  String email1 = sc.nextLine();

                    System.out.println("Enter your password : "); String password = sc.nextLine(); border(); animate();

                    //check if account exists:
                    if(dao.login_checking_account(email1, password) && dao.login_savings_account(email1, password) ){
                        System.out.println("You have Both Saving and Checking Account... Which one Would you like to login to?");
                        System.out.println("1. Checking \n2. Savings");

                        int pick = Integer.parseInt(sc.nextLine());
                        switch (pick){
                            case 1 -> {
                                dao.login_checking_account(email1, password);
                                System.out.println("You are logged in to Checking Account");
                                CheckingAccountMenu.myOptions(email1);

                            }
                            case 2->{
                                dao.login_savings_account(email1, password);
                                System.out.println("You are logged in to Savings Account");
                                SavingsAccountMenu.myOptions(email1);
                            }
                        }

                    }else if (dao.login_checking_account(email1, password)) {
                        System.out.println("You are logged in to Checking Account");

                        CheckingAccountMenu.myOptions(email1);
                    } else if (dao.login_savings_account(email1, password)) {
                        System.out.println("You are logged in to Savings Account");
                        SavingsAccountMenu.myOptions(email1);
                    } else {
                        animate();
                        System.out.println("Login Failed Please Try Again");
                        animate();
                        border();
                        sleep();
                    }
                }


                case 4 -> {
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    border();
                    animate();
                    if (dao.check_if_employee_exists(email, password)) {
                        System.out.println("Login Successful");
                        EmployeeMenu.displayMenu(email);
                    } else {
                        animate();
                        border();
                        System.out.println("\nLogin Failed Please Try Again");
                        border();
                    }

                }
                case 5 -> {
                    System.out.println("Thank you for Banking with People's Bank");
                    flag = false;
                    exit(0);
                }

            }
        }

    }
}
