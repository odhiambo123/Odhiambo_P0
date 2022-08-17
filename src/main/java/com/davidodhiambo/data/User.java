package com.davidodhiambo.data;

public class User {
    enum UserType {
        ADMIN,
        EMPLOYEE,
        CUSTOMER
    }
    enum AccountType {
        CHECKING,
        SAVINGS
    }
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private UserType userType;
    private AccountType accountType;
    private int accountnumber;
    private double balance;


    public User(int id, String fname, String lname, String email, String password, UserType userType, AccountType accountType, int accountnumber, double balance) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.accountType = accountType;
        this.accountnumber = accountnumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public int getAccountnumber() {
        return accountnumber;
    }
    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + ", userType=" + userType + ", accountType=" + accountType + ", accountnumber=" + accountnumber + ", balance=" + balance + '}';
    }

}

