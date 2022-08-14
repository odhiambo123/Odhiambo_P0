package com.davidodhiambo.service;

import com.davidodhiambo.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDaoImpl implements BankDao {

    Connection conn;

    public BankDaoImpl() {
        conn = MyConnectionJDBC.getConnection();

    }


    @Override
    public void addEmployee(EmployeeModel employee) throws SQLException {
        String sql = "INSERT INTO employee (fname, lname, email) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, employee.getFname());
        ps.setString(2, employee.getLname());
        ps.setString(3, employee.getEmail());
        int count = ps.executeUpdate();
        if(count > 0){
            System.out.println("Employee added successfully");
        }else {
            System.out.println("Employee not added Something went wrong");
        }

    }

    @Override
    public void updateemployee(EmployeeModel employee) throws SQLException {
        String sql = "UPDATE employee SET fname = ?, lname = ?, email = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, employee.getFname());
        ps.setString(2, employee.getLname());
        ps.setString(3, employee.getEmail());
        ps.setInt(4, employee.getId());
        int count = ps.executeUpdate();
        if(count > 0){
            System.out.println("Employee updated successfully");
        }else {
            System.out.println("Employee not updated Something went wrong");
        }

    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int count = ps.executeUpdate();
        if(count > 0){
            System.out.println("Employee deleted successfully");
        }else {
            System.out.println("Employee not deleted Something went wrong");
        }

    }

    @Override
    public List<EmployeeModel> getAllEmployees() throws SQLException {
        List<EmployeeModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String email = rs.getString(4);
            EmployeeModel employee = new EmployeeModel(id, fname, lname, email);
            employees.add(employee);
        }
        return employees;

    }

    @Override
    public EmployeeModel getEmployeeById(int empid) throws SQLException {
        EmployeeModel employee = new EmployeeModel();
        String sql = "SELECT * FROM employee WHERE id = " + empid;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        int id = rs.getInt(1);
        String fname = rs.getString(2);
        String lname = rs.getString(3);
        String email = rs.getString(4);
        employee = new EmployeeModel(id, fname, lname, email);

        return employee;
    }

    @Override
    public void deleteCheckingAccount(int accnumber) throws SQLException {
        String sql = "DELETE FROM checkingaccount WHERE accnumber = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, accnumber);
        int count = ps.executeUpdate();
        if(count > 0){
            System.out.println("Cheking account deleted successfully");
        }else {
            System.out.println("Cheking account not deleted Something went wrong");
        }
    }

    @Override
    public void deleteSavingsAccount(int accnumber) throws SQLException {
        String sql = "DELETE FROM savingsaccount WHERE accnumber = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, accnumber);
        int count = ps.executeUpdate();
        if(count > 0){
            System.out.println("Savings account deleted successfully");
        }else {
            System.out.println("Savings account not deleted Something went wrong");
        }

    }

    @Override
    public void addSavingsAccount(SavingsAccountModel savingsaccount) throws SQLException {

    }

    @Override
    public void addCheckingAccount(CheckingAccountModel chekingaccount) throws SQLException {

    }

}
