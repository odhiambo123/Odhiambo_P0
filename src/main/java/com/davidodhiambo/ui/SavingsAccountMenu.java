package com.davidodhiambo.ui;

import com.davidodhiambo.service.MyConnectionJDBC;

import java.util.Scanner;

public class SavingsAccountMenu {
    public static void border() {
        System.out.println("============================================\n============================================");
    }

    public static void animate() {
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
                System.out.println("Your balance is : " + MyConnectionJDBC.get_Savings_balance(email));
            }
            case 2 -> {
                System.out.println("Enter email : ");
                String email = sc.nextLine();
                System.out.println("Enter amount to deposit : ");
                double amount = Double.parseDouble(sc.nextLine());
                border();
                System.out.println("\n...One moment please... : ");
                //animation
                animate();
                MyConnectionJDBC.deposit_Savings_account(amount, email);
                System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(email));

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
                MyConnectionJDBC.withdraw_Savings_account(amount, email);
                System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(email));
            }
            case 4 -> {
                System.out.println("Chose an account to transfer to : ");
                System.out.println("1. My Checking account");
                System.out.println("2. another checking account");
                System.out.println("3. another savings account");
                int choice2 = Integer.parseInt(sc.nextLine());

                switch (choice2) {
                    case 1 -> {
                        System.out.println("Enter amount to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter your email : ");
                        String email = sc.nextLine();
                        border();
                        System.out.println("\n...One moment please... : ");
                        //check if the checking account exists
                        if (MyConnectionJDBC.check_checking_account(email)) {
                            //animation
                            animate();
                            MyConnectionJDBC.transfer_Savings_to_Checking(amount, email);
                            System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(email));
                            //animation
                            animate();
                            System.out.println("Your balance is : ");
                            MyConnectionJDBC.transfer_Savings_to_Checking(amount, email);
                            System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(email));
                        }
                        else {
                            System.out.println("\n...Sorry!.. your checking account does not exist");
                        }

                    }

                    case 2 -> {
                        System.out.println("How much do you want to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter your email : ");
                        String senders_email = sc.nextLine();
                        System.out.println("Enter the receiver's email : ");
                        String receivers_email = sc.nextLine();
                        border();
                        System.out.println("\n...One moment please... : ");
                        //animation
                        animate();
                        //check if the recipients checking account exists
                        if (MyConnectionJDBC.check_checking_account(receivers_email)){
                            animate();
                            MyConnectionJDBC.transfer_Savings_to_outside_Checking(amount, senders_email, receivers_email);
                            System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(senders_email));
                        }
                        else {
                            System.out.println("\n...Sorry!.. That checking account does not exist");
                        }


                    }

                    case 3 -> {
                        System.out.println("How much do you want to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter your email : ");
                        String senders_email = sc.nextLine();
                        System.out.println("Enter the receiver's email : ");
                        String receivers_email = sc.nextLine();
                        border();
                        System.out.println("\n...One moment please... : ");
                        //animation
                        animate();
                        //check if the recipients savings account exists
                        if (MyConnectionJDBC.check_if_savings_account_exists(receivers_email)){
                            animate();
                            MyConnectionJDBC.transfer_Savings_to_outside_Savings(amount, senders_email, receivers_email);
                            System.out.println("\n...Thank you!.. your new balance is : " + MyConnectionJDBC.get_Savings_balance(senders_email));
                        }
                        else {
                            System.out.println("\n...Sorry!.. That savings account does not exist");
                        }

                    }


                }


            }
        }
    }
}