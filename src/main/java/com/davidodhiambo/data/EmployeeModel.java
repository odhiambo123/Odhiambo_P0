package com.davidodhiambo.data;

public class EmployeeModel {
    private int id;
    private String fname;
    private String lname;
    private String email;

    public EmployeeModel(int id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;

    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
        return "EmployeeModel{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }
}
