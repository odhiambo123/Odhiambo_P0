package com.davidodhiambo.data;

import java.sql.SQLException;
import java.util.List;

public interface BankDao {
    void addEmployee(EmployeeModel employee) throws SQLException;
    void updateemployee(EmployeeModel employee) throws SQLException;
    void deleteEmployee(int id) throws SQLException;
    List<EmployeeModel> getAllEmployees() throws SQLException;
    EmployeeModel getEmployeeById(int id) throws SQLException;
}
