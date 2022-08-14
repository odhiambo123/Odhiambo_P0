package com.davidodhiambo.service;

import com.davidodhiambo.ui.CheckingAccountMenu;
import com.davidodhiambo.ui.EmployeeMenu;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MyConnectionJDBC {
    private static Connection connection = null;
    private MyConnectionJDBC() {}

       public static Connection getConnection(){
        //*******************************************************************
        try(InputStream input = new FileInputStream("src/main/java/com/davidodhiambo/service/config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            //Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return connection;
       }

    public static boolean checkem_if_employee_exists(String email, String password) {
        String sql = "SELECT * FROM employee WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Welcome " + rs.getString("fname") + " " + rs.getString("lname"));
                EmployeeMenu.displayMenu();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void check_if_checking_account_exists(String email, String password) {
        String sql = "SELECT * FROM checkingaccount WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Welcome " + rs.getString("fname") + " " + rs.getString("lname"));
                CheckingAccountMenu.myOptions();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void add_new_checking_account(String fname, String lname, String email, String password, Double deposit,Double balance) {
        String sql = "INSERT INTO checkingaccount (fname,lname, email, password,deposit,balance) VALUES (?, ?, ?, ?,?,?)";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.deposit + checkingaccount.balance) WHERE email = ?";
        String sql3 = "UPDATE checkingaccount SET deposit = 0 WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setDouble(5, deposit);
            ps.setDouble(6, balance);
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, email);
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.setString(1, email);
            ps3.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Account pending approval");
        System.out.println("what would you like to do next?");
    }

    public static void add_new_savings_account(String fname, String lname, String email, String password, Double deposit,Double balance) {
        String sql = "INSERT INTO savingsaccount (fname, lname, email, password, deposit, balance) VALUES (?, ?, ?, ?,?,?)";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.deposit + savingsaccount.balance) WHERE email = ?";
        String sql3 = "UPDATE savingsaccount SET deposit = 0 WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setDouble(5, deposit);
            ps.setDouble(6, balance);
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, email);
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement(sql3);
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

    public static String get_Checking_balance(String email) {
        String sql = "SELECT balance FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void deposit_Checking_account(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static void withdraw_Checking_account(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static void transfer_Checking_to_Savings(double amount, String email) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static String get_Savings_balance(String email) {
        String sql = "SELECT balance FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static void transfer_Checking_to_outside_Checking(double amount, String recipientEmail, String yourEmail) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, yourEmail);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, recipientEmail);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static boolean check_if_checking_account_exists(String recipientEmail) {
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public static boolean check_if_savings_account_exists(String recipientEmail) {
        String sql = "SELECT email FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public static void transfer_Checking_to_outside_Savings(double amount, String recipientEmail, String yourEmail) {
        String sql = "UPDATE checkingaccount SET balance = (checkingaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, yourEmail);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, recipientEmail);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static boolean login_checking_account(String email, String password) {
        //check if the account exists
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //check if the password is correct
                String sql2 = "SELECT password FROM checkingaccount WHERE email = ?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
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

    public static boolean login_savings_account(String email, String password) {
        //check if the account exists
        String sql = "SELECT email FROM savingsaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //check if the password is correct
                String sql2 = "SELECT password FROM savingsaccount WHERE email = ?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
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

    public static void deposit_Savings_account(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static void withdraw_Savings_account(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    public static void transfer_Savings_to_Checking(double amount, String email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static boolean check_checking_account(String email) {
        String sql = "SELECT email FROM checkingaccount WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public static void transfer_Savings_to_outside_Checking(double amount, String senders_email, String receivers_email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE checkingaccount SET balance = (checkingaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, senders_email);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, receivers_email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public static void transfer_Savings_to_outside_Savings(double amount, String senders_email, String receivers_email) {
        String sql = "UPDATE savingsaccount SET balance = (savingsaccount.balance - ?) Where email = ?";
        String sql2 = "UPDATE savingsaccount SET balance = (savingsaccount.balance + ?) Where email = ?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, senders_email);
            ps.executeUpdate();
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setDouble(1, amount);
            ps2.setString(2, receivers_email);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
