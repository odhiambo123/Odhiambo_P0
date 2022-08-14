package com.davidodhiambo.data;

public class CheckingAccountModel {
    private int accnumber;
    private double balance;
    private String fname;
    private String lname;
    private String email;

    public CheckingAccountModel(int accnumber, double balance, String fname, String lname, String email) {
        this.accnumber = accnumber;
        this.balance = balance;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public CheckingAccountModel() { // default constructor for the class

    }

    public int getAccnumber() {
        return accnumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public void setAccnumber(int accnumber) {
        this.accnumber = accnumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override // Override the toString method to display the data in a meaningful way.
    public String toString() {
        return "ChekingAccountModel{" + "accnumber=" + accnumber + ", balance=" + balance + ", fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }
}
