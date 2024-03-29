package com.davidodhiambo.service;

import com.davidodhiambo.data.*;
import com.davidodhiambo.ui.CheckingAccountMenu;
import com.davidodhiambo.ui.EmployeeMenu;

import java.sql.*;

//import static com.davidodhiambo.service.MyConnectionJDBC.connection;

public class BankDaoImpl implements BankDao {

    static Connection conn;

    public BankDaoImpl() {
        conn = MyConnectionJDBC.getConnection();

    }

    public boolean check_if_employee_exists(String email, String password) {
        String sql_check_if_exists = "SELECT * FROM employee WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_check_if_exists);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Welcome " + rs.getString("fname") + " " + rs.getString("lname"));
                EmployeeMenu.displayMenu(email);
            }
        } catch (SQLException e) {
            System.out.println("Whoops! Make sure Employee exists with that name..");;
        }
        return false;
    }

    public static void check_if_checking_account_exists(String email, String password) {
        String sql_select_all_from_checking = "SELECT * FROM checkingaccount WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_select_all_from_checking);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Welcome " + rs.getString("fname") + " " + rs.getString("lname"));
                CheckingAccountMenu.myOptions(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void add_new_checking_account(String fname, String lname, String email, String password, Double deposit, Double balance) {
        String sql_insert = "INSERT INTO checkingaccount (fname,lname, email, password,deposit,balance) VALUES (?, ?, ?, ?,?,?)";
        String sql_update_checking_balance = "UPDATE checkingaccount SET balance = (checkingaccount.deposit + checkingaccount.balance) WHERE email = ?";
        String sql_update_checking_deposit = "UPDATE checkingaccount SET deposit = 0 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_insert);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setDouble(5, deposit);
            ps.setDouble(6, balance);
            ps.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(sql_update_checking_balance);
            ps2.setString(1, email);
            ps2.executeUpdate();
            PreparedStatement ps3 = conn.prepareStatement(sql_update_checking_deposit);
            ps3.setString(1, email);
            ps3.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Account pending approval");
        System.out.println("what would you like to do next?");
    }

    public void add_new_savings_account(String fname, String lname, String email, String password, Double deposit, Double balance) {
        String sql = "INSERT INTO savingsaccount (fname, lname, email, password, deposit, balance) VALUES (?, ?, ?, ?,?,?)";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.deposit + savingsaccount.balance) WHERE email = ?";
        String sql3 = "UPDATE savingsaccount SET deposit = 0 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setDouble(5, deposit);
            ps.setDouble(6, balance);
            ps.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, email);
            ps2.executeUpdate();
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setString(1, email);
            ps3.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("==============================================================");
        System.out.println("Account pending approval");
        System.out.println("what would you like to do next?");
        System.out.println("==============================================================");
    }

    public double get_Checking_balance(String email) {
        String sql = "SELECT balance FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }

    public void deposit_Checking_account(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void withdraw_Checking_account(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void transfer_Checking_to_Savings(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public double get_Savings_balance(String email) {
        String sql = "SELECT balance FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }


    public void transfer_Checking_to_outside_Checking(double amount, String recipientEmail, String yourEmail) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, yourEmail);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, recipientEmail);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean check_if_checking_account_exists(String recipientEmail) {
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, recipientEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("The acount doesnt exist...");
        }
        return false;
    }

    public boolean check_if_savings_account_exists(String recipientEmail) {
        String sql = "SELECT email FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, recipientEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("The acount doesnt exist...");
        }
        return false;
    }

    public void transfer_Checking_to_outside_Savings(double amount, String recipientEmail, String yourEmail) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, yourEmail);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, recipientEmail);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean login_checking_account(String email, String password) {
        //check if the account exists
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //check if the password is correct
                String sql2 = "SELECT password FROM checkingaccount WHERE email = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    if (rs2.getString("password").equals(password)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean login_savings_account(String email, String password) {
        //check if the account exists
        String sql = "SELECT email FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //check if the password is correct
                String sql2 = "SELECT password FROM savingsaccount WHERE email = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    if (rs2.getString("password").equals(password)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void deposit_Savings_account(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void withdraw_Savings_account(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            if (get_Savings_balance(email) < amount) {
                System.out.println("\nYou do not have enough money in your savings account");
            } else {
                ps.executeUpdate();
            }
            //ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    public void transfer_Savings_to_Checking(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean check_checking_account(String email) {
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("The acount doesnt exist...");
        }
        return false;
    }

    public void transfer_Savings_to_outside_Checking(double amount, String senders_email, String receivers_email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, senders_email);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, receivers_email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void transfer_Savings_to_outside_Savings(double amount, String senders_email, String receivers_email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, senders_email);
            ps.executeUpdate();
            PreparedStatement ps2= conn.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, receivers_email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void update_checking_account_email_and_password(String email, String password, String new_email, String new_password) {
        String sql = "UPDATE checkingaccount SET email = ?, password = ? WHERE email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, new_email);
            ps.setString(2, new_password);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void update_savings_account_email_and_password(String email, String password, String new_email, String new_password) {
        String sql = "UPDATE savingsaccount SET email = ?, password = ? WHERE email = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, new_email);
            ps.setString(2, new_password);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void delete_checking_account(String email, String password) {
        String sql = "DELETE FROM checkingaccount WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void delete_savings_account(String email, String password) {
        String sql = "DELETE FROM savingsaccount WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void list_All_Employees() {
        String sql = "CALL list_all_my_employees()";//"SELECT * FROM employee";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void approve_checking_account(String email) {
        String sql = "UPDATE checkingaccount SET approved = 1 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("Congratulations!! Account approved \n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void approve_savings_account(String email) {
        String sql = "UPDATE savingsaccount SET approved = 1 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("Congratulations!! Account approved \n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reject_checking_account(String email) {
        String sql = "UPDATE checkingaccount SET approved = 0 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reject_savings_account(String email) {
        String sql = "UPDATE savingsaccount SET approved = 0 WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean check_if_savings_acount_is_approved(String email) {
        String sql = "SELECT approved FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("approved");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean check_if_checking_acount_is_approved(String email) {
        String sql = "SELECT approved FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("approved") == 1) {
                    System.out.println("The account is approved");
                } else {
                    System.out.println("The account is not approved yet");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void view_database_logs() {
        String sql = "SELECT * FROM mysql.general_log;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("log"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void add_Employee(String fname, String lname, String email, String password) {
        String sql = "INSERT INTO employee (fname, lname, email, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete_Employee(String email) {
        String sql = "DELETE FROM employee WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Whoops!, No employee matches the data entered!!");;
        }
    }

    public static void update_Employee(String email, String fname, String lname, String password) {
        String sql = "UPDATE employee SET fname = ?, lname = ?, password = ? WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean check_if_checking_account_is_approved(String email) {
        String sql = "SELECT approved FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("approved") != 0;
            }
        } catch (SQLException e) {
            System.out.println("Whoops please we didn't find an  approved account with those credentials in the system!");
        }
        return false;
    }

}
