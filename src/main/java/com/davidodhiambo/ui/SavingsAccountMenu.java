package com.davidodhiambo.ui;

import com.davidodhiambo.data.BankDao;
import com.davidodhiambo.data.BankDaoFactory;
import com.davidodhiambo.service.MyConnectionJDBC;

import java.util.Scanner;

import static java.lang.System.exit;

public class SavingsAccountMenu {
    static BankDao dao = BankDaoFactory.getAccounts();
    public static void border() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n*----------------------------------------*");
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
                animate();
                System.out.println("Your balance is : " + dao.get_Savings_balance(email1));
                border();
                myOptions(email1);
            }
            case 2 -> {

                System.out.println("Enter amount to deposit : ");
                double amount = Double.parseDouble(sc.nextLine());
                if (amount > 0) {
                    animate();
                    dao.deposit_Savings_account(amount, email1);
                    System.out.println("You have deposited " + amount + " to your account, new balance is " + dao.get_Savings_balance(email1));
                    border();
                    myOptions(email1);
                } else {
                    System.out.println("Invalid amount");
                    border();
                    myOptions(email1);
                }

            }
            case 3 -> {
                System.out.println("Enter amount to withdraw : ");
                double amount = Double.parseDouble(sc.nextLine());
                if (amount > 0) {
                    animate();
                    dao.withdraw_Savings_account(amount, email1);
                    System.out.println("You have withdrawn " + amount + " from your account, new balance is " + dao.get_Savings_balance(email1));
                    border();
                    myOptions(email1);
                } else {
                    System.out.println("Invalid amount");
                    border();
                    myOptions(email1);
                }

            }
            case 4 -> {
                border();
                System.out.println("Chose an account to transfer to : ");
                System.out.println("1. My Checking account");
                System.out.println("2. another checking account");
                System.out.println("3. another savings account");
                System.out.println("4 exit");
                int choice2 = Integer.parseInt(sc.nextLine());

                switch (choice2) {
                    case 1 -> {
                        System.out.println("Enter amount to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());

                        border();
                        System.out.println("\n...One moment please... : ");
                        //check if the checking account exists
                        if (dao.check_checking_account(email1) && amount > 0) {
                            //animation
                            animate();
                            dao.transfer_Savings_to_Checking(amount, email1);
                            System.out.println("\n...Thank you!.. your new balance is : " + dao.get_Savings_balance(email1));
                            //animation
                            animate();
                            System.out.println("Your balance is : ");
                            dao.transfer_Savings_to_Checking(amount, email1);
                            border();
                            System.out.println("\n...Thank you!.. your new balance is : " + dao.get_Savings_balance(email1));
                            border();
                            myOptions(email1);
                        }
                        else {
                            System.out.println("\n...Sorry!.. Either your checking account does not exist or you have entered an invalid amount");
                            myOptions(email1);
                        }

                    }

                    case 2 -> {
                        System.out.println("How much do you want to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter the receiver's email : ");
                        String receivers_email = sc.nextLine();
                        border();
                        System.out.println("\n...One moment please... : ");
                        //animation
                        animate();
                        //check if the recipients checking account exists
                        if (dao.check_checking_account(receivers_email) && amount > 0) {
                            animate();
                            border();
                            dao.transfer_Savings_to_outside_Checking(amount, email1, receivers_email);
                            System.out.println("\n...Thank you!.. your new balance is : " + dao.get_Savings_balance(email1));
                            border();
                            myOptions(email1);
                        }
                        else {
                            System.out.println("\n...Sorry!.. That checking account does not exist or you have entered an invalid amount");
                            myOptions(email1);
                        }


                    }

                    case 3 -> {
                        System.out.println("How much do you want to transfer : ");
                        double amount = Double.parseDouble(sc.nextLine());
                        /*System.out.println("Enter your email : ");
                        String senders_email = sc.nextLine()*/;
                        System.out.println("Enter the receiver's email : ");
                        String receivers_email = sc.nextLine();
                        border();
                        System.out.println("\n...One moment please... : ");
                        //animation
                        animate();
                        //check if the recipients savings account exists
                        if (dao.check_if_savings_account_exists(receivers_email) && amount > 0 && (amount <= dao.get_Savings_balance(email1))) {
                            animate();
                            dao.transfer_Savings_to_outside_Savings(amount, email1, receivers_email);
                            border();
                            System.out.println("\n...Thank you!.. your new balance is : " + dao.get_Savings_balance(email1));
                            border();
                            myOptions(email1);
                        }
                        else {
                            System.out.println("\n...Sorry!.. That savings account does not exist");
                            myOptions(email1);
                        }

                    }
                    case 4 -> {
                        animate();
                        System.out.println("Exiting...");
                        exit(0);
                    }


                }


            }
        }
    }
}