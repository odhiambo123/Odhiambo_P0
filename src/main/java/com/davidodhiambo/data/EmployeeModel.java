package com.davidodhiambo.data;

public class EmployeeModel {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private int salary;


    public EmployeeModel(int id, String fname, String lname, String email, int salary) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.salary = salary;

    }

    public EmployeeModel() {

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
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email +  ", salary=" + salary + '}';
    }
}
