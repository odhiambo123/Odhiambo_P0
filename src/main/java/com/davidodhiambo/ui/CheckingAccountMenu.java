package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.data.BankDaoFactory;
import com.davidodhiambo.service.MyConnectionJDBC;

import java.io.Console;
import java.util.Scanner;

public class CheckingAccountMenu {
    static BankDao dao = BankDaoFactory.getAccounts();
    public static void border() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n*----------------------------------------*");
    }
    public static void animate(){
        for (int i = 0; i < 3; i++) {
            System.out.print("-->");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private static Scanner sc = new Scanner(System.in);
    public static void myOptions(String email1) {
        border();
        System.out.println("1. View Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Logout");
        border();
        System.out.println("Please enter your choice : \n");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> {
                /*System.out.println("Enter your email : ");
                String email = sc.nextLine();*/
                animate();
                border();
                System.out.println("Your balance is : " + dao.get_Checking_balance(email1));
                border();
                myOptions(email1);
            }
            case 2 -> {
                /*System.out.println("Enter your email : ");
                String email = sc.nextLine();*/
                System.out.println("Enter amount to deposit : ");
                double amount = Double.parseDouble(sc.nextLine());
                if (amount < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    myOptions(email1);
                }
                border();
                System.out.println("\n...One moment please... : ");
                //animation
                   animate();
                   border();
                dao.deposit_Checking_account(amount, email1);
                System.out.println("\n...Thank you!.. your new balance is : " + dao.get_Checking_balance(email1));
                border();
                myOptions(email1);

            }
            case 3 -> {
                System.out.println("Enter amount to withdraw : ");
                double amount = Double.parseDouble(sc.nextLine());
                double balance = dao.get_Checking_balance(email1);
                if (amount < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    myOptions(email1);
                } else if ( amount > balance) {
                    System.out.println("Whoops!! You do not have enough balance to withdraw. Please try again.");
                    myOptions(email1);

                }
                System.out.println("\n...One moment please... : ");
                //animation
                   animate();
                dao.withdraw_Checking_account(amount, email1);
                border();
                System.out.println("succcesfully withdrew " + amount + " from your checking account");

                System.out.println("Your new balance is : " + dao.get_Checking_balance(email1));
                border();
                myOptions(email1);
            }
            case 4 -> {
                System.out.println("Chose an account to transfer to : ");
                System.out.println("1. My savings account");
                System.out.println("2. another checking account");
                System.out.println("3. another savings account");
                int myChoice = Integer.parseInt(sc.nextLine());
                switch(myChoice){
                    case 1 ->{
                        System.out.println("Enter amount to transfer : ");
                        double amount = Double.parseDouble(sc.next());
                        if (amount < 0) {
                            System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                            myOptions(email1);
                        } else if ( amount > dao.get_Checking_balance(email1)) {
                            System.out.println("Whoops!! You do not have enough balance to transfer. Please try again.");
                            myOptions(email1);
                        }
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                        dao.transfer_Checking_to_Savings(amount, email1);
                        System.out.println("Your Checking balance is : " + dao.get_Checking_balance(email1));
                        System.out.println("Your Savings balance is : " + dao.get_Savings_balance(email1));

                    }
                    case 2 -> {
                        if (dao.check_if_checking_acount_is_approved(email1)){
                        System.out.println("Enter the amout to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter recipient email : ");
                        String recipientEmail = sc.nextLine();
                        if (amount < 0) {
                            System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                            myOptions(email1);
                        } else if ( amount > dao.get_Checking_balance(email1)) {
                            System.out.println("Whoops!! You do not have enough balance to transfer. Please try again.");
                            myOptions(email1);
                        }
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                           //check if the recipient has a checking account
                            if(dao.check_if_checking_account_exists(recipientEmail)){
                        dao.transfer_Checking_to_outside_Checking(amount, recipientEmail, email1);
                        System.out.println("Your Checking balance is : " + dao.get_Checking_balance(email1));
                        System.out.println("Your Savings balance is : " + dao.get_Savings_balance(email1));
                            }else{
                                System.out.println("The recipient does not have a checking account");
                            }
                    }else {
                        System.out.println("You do not have an approved account");
                    }
                    }

                    case 3 -> {
                        if (dao.check_if_savings_acount_is_approved(email1)){
                        System.out.println("Enter the amout you want to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter recipient email : ");
                        String recipientEmail = sc.nextLine();
                        if (amount < 0) {
                            System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                            myOptions(email1);
                        } else if ( amount > dao.get_Checking_balance(email1)) {
                            System.out.println("Whoops!! You do not have enough balance to transfer. Please try again.");
                            myOptions(email1);
                        }
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                           //check if the recipient has a checking account
                            if(dao.check_if_savings_account_exists(recipientEmail)){
                        dao.transfer_Checking_to_outside_Savings(amount, recipientEmail, email1);
                        System.out.println("Your Checking balance is : " + dao.get_Checking_balance(email1));
                        System.out.println("Your Savings balance is : " + dao.get_Savings_balance(email1));
                            }else{
                                System.out.println("The recipient does not have a savings account");
                            }
                    }else {
                        System.out.println("You do not have an approved account");
                    }
                    }
                }

            }
            case 5 -> {
                System.out.println("Thank you for banking with us");
                System.out.println("Goodbye");
                System.exit(0);
            }
            default -> {
                System.out.println("Please enter a valid choice");

            }
        }
    }
}
