package com.davidodhiambo.ui;

import com.davidodhiambo.service.MyConnectionJDBC;

import java.io.Console;
import java.util.Scanner;

public class CheckingAccountMenu {
    public static void border() {
        System.out.println("============================================\n============================================");
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
    public static void myOptions() {
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
                System.out.println("Enter your email : ");
                String email = sc.nextLine();
                animate();
                System.out.println("Your balance is : " + MyConnectionJDBC.get_Checking_balance(email));
            }
            case 2 -> {
                System.out.println("Enter your email : ");
                String email = sc.nextLine();
                System.out.println("Enter amount to deposit : ");
                double amount = Double.parseDouble(sc.nextLine());
                border();
                System.out.println("\n...One moment please... : ");
                //animation
                   animate();


                MyConnectionJDBC.deposit_Checking_account(amount, email);

            }
            case 3 -> {
                System.out.println("Enter amount to withdraw : ");
                double amount = Double.parseDouble(sc.nextLine());
                System.out.println("Enter your email : ");
                String email = sc.nextLine();
                border();
                System.out.println("\n...One moment please... : ");
                //animation
                   animate();
                System.out.println("Your balance is : ");
                MyConnectionJDBC.withdraw_Checking_account(amount, email);

                System.out.println("Your new balance is : " + MyConnectionJDBC.get_Checking_balance(email));
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
                        System.out.println("Enter your email : ");
                        String email = sc.next();
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                        MyConnectionJDBC.transfer_Checking_to_Savings(amount, email);
                        System.out.println("Your Checking balance is : " + MyConnectionJDBC.get_Checking_balance(email));
                        System.out.println("Your Savings balance is : " + MyConnectionJDBC.get_Savings_balance(email));

                    }
                    case 2 -> {
                        System.out.println("Enter the amout to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter recipient email : ");
                        String recipientEmail = sc.nextLine();
                        System.out.println("Enter your email : ");
                        String yourEmail = sc.nextLine();
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                           //check if the recipient has a checking account
                            if(MyConnectionJDBC.check_if_checking_account_exists(recipientEmail)){
                        MyConnectionJDBC.transfer_Checking_to_outside_Checking(amount, recipientEmail, yourEmail);
                        System.out.println("Your Checking balance is : " + MyConnectionJDBC.get_Checking_balance(yourEmail));
                        System.out.println("Your Savings balance is : " + MyConnectionJDBC.get_Savings_balance(yourEmail));
                            }else{
                                System.out.println("The recipient does not have a checking account");
                            }
                    }

                    case 3 -> {
                        System.out.println("Enter the amout to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter recipient email : ");
                        String recipientEmail = sc.nextLine();
                        System.out.println("Enter your email : ");
                        String yourEmail = sc.nextLine();
                        System.out.println("...One moment please... : ");
                        //animation
                           animate();
                           //check if the recipient has a checking account
                            if(MyConnectionJDBC.check_if_savings_account_exists(recipientEmail)){
                        MyConnectionJDBC.transfer_Checking_to_outside_Savings(amount, recipientEmail, yourEmail);
                        System.out.println("Your Checking balance is : " + MyConnectionJDBC.get_Checking_balance(yourEmail));
                        System.out.println("Your Savings balance is : " + MyConnectionJDBC.get_Savings_balance(yourEmail));
                            }else{
                                System.out.println("The recipient does not have a savings account");
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
