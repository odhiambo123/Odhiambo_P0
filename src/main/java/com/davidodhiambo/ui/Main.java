package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.data.BankDaoFactory;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void banner(){
        System.out.println("""
                __________                         .__           /\\          __________                  __   \s
                \\______   \\  ____    ____  ______  |  |    ____  )/  ______  \\______   \\_____     ____  |  | __
                 |     ___/_/ __ \\  /  _ \\ \\____ \\ |  |  _/ __ \\    /  ___/   |    |  _/\\__  \\   /    \\ |  |/ /
                 |    |    \\  ___/ (  <_> )|  |_> >|  |__\\  ___/    \\___ \\    |    |   \\ / __ \\_|   |  \\|    <\s
                 |____|     \\___  > \\____/ |   __/ |____/ \\___  >  /____  >   |______  /(____  /|___|  /|__|_ \\
                                \\/         |__|               \\/        \\/           \\/      \\/      \\/      \\/""");
    }
    public static void border() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n*----------------------------------------*");
    }
    public static void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("system interrupted...");
        }

    }
    public static void animate(){
        for (int i = 0; i < 3; i++) {
            System.out.print(" > ");
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws SQLException {

        //read from properties file
        border();
        //dao
        BankDao dao = BankDaoFactory.getAccounts();
        Scanner sc = new Scanner(System.in);

        banner();

        System.out.println("Welcome to your Bank of the People \n  \n[Requirements-1]: All Data is stored in a database. Access through JDBC~~");

        border();
        boolean flag = true;
        while (flag) {
            System.out.println("Are you a new customer, an existing customer or employee?");
            System.out.println("1. New Checking Account");
            System.out.println("2. New Savings Account");
            System.out.println("3. Login");
            //System.out.println("4. Login to an existing Savings account");
            System.out.println("4. Login as an Employee");
            System.out.println("5. Exit \n \n[Requirement-2] A custom stored procedure is called to perform some portion of the functionality.\nget_all_my_employees().\n");


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
                    double deposit = Double.parseDouble(sc.nextLine());
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
                    System.out.println("Enter Account owner first name : ");
                    String fname = sc.nextLine();
                    System.out.println("Last name : ");
                    String lname = sc.nextLine();
                    System.out.println("Email : ");
                    String email = sc.nextLine();
                    System.out.println("Password : ");
                    String password = sc.nextLine();
                    System.out.println("Initial Deposit : ");
                    border();
                    double deposit = Double.parseDouble(sc.nextLine());
                    if (deposit < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }
                    animate();
                    dao.add_new_savings_account(fname, lname, email, password, deposit, 0.0);
                    border();
                    System.out.println("Thank you Account pending approval...");
                    border();


                }
                case 3 -> {
                    System.out.println("Enter your email : ");
                    String email1 = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    border();
                    animate();
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
