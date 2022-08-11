package com.davidodhiambo.data;

public class BankModel {
    private enum AccountType {
        checking, savidngd
    }
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private AccountType accountType;
    private int balance;


    public BankModel(int id, String name, String address, String phone, String email, AccountType accountType, int balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.accountType = accountType;
        this.balance = balance;
    }

    public BankModel() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankModel{" + "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", accountType=" + accountType + ", balance=" + balance + '}';
    }


    
}
