package com.davidodhiambo.data;

public interface BankDao {

    boolean login_checking_account(String email, String password);

    void add_new_savings_account(String fname, String lname, String email, String password, Double deposit, Double balance);

    void add_new_checking_account(String fname, String lname, String email, String password, Double deposit, Double balance);

    void transfer_Checking_to_outside_Checking(double amount, String recipientEmail, String yourEmail);

    boolean check_if_checking_account_exists(String recipientEmail);
    public boolean check_if_savings_account_exists(String recipientEmail);

    void transfer_Checking_to_outside_Savings(double amount, String recipientEmail, String yourEmail);

    String get_Checking_balance(String yourEmail);

    String get_Savings_balance(String email);

    void deposit_Savings_account(double amount, String email);

    void withdraw_Savings_account(double amount, String email);

    boolean check_checking_account(String email);

    void transfer_Savings_to_Checking(double amount, String email);

    void transfer_Savings_to_outside_Checking(double amount, String senders_email, String receivers_email);

    void transfer_Savings_to_outside_Savings(double amount, String senders_email, String receivers_email);

    void deposit_Checking_account(double amount, String email);

    void withdraw_Checking_account(double amount, String email);

    void transfer_Checking_to_Savings(double amount, String email);

    boolean login_savings_account(String email, String password);

    boolean checkem_if_employee_exists(String email, String password);

    void update_checking_account_email_and_password(String email, String password, String new_email, String new_password);

    void update_savings_account_email_and_password(String email, String password, String new_email, String new_password);

    void delete_checking_account(String email, String password);

    void delete_savings_account(String email, String password);

    void list_All_Employees();

    void approve_checking_account(String email);

    void approve_savings_account(String email);

    void reject_checking_account(String email);

    void reject_savings_account(String email);


}