package com.davidodhiambo.data;

public class SavingsAccountModel {
    private int accnumber;

    private String fname;
    private String lname;
    private String email;
    private double deposit;


    public SavingsAccountModel(int accnumber, double deposit, String fname, String lname, String email) {
        this.accnumber = accnumber;
        this.deposit = deposit;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public SavingsAccountModel() {

    }

    public int getAccnumber() {
        return accnumber;
    }

    public double getDeposit() {
        return deposit;
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

    public void setDeposit(double deposit) {
        this.deposit = deposit;
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

    @Override
    public String toString() {
        return "SavingsAccountModel{" + "accnumber=" + accnumber + ", balance=" + deposit + ", fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }



}

