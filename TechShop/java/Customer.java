package com.AssignmentTechshop;

import java.sql.*;
import java.util.Scanner;

public class CustomerService {

    private final Connection conn;

    public CustomerService(Connection conn) {
        this.conn = conn;
    }

    public void addCustomer() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter First Name: ");
            String firstName = sc.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            String query = "INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Customer added successfully." : "Failed to add customer.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void viewCustomers() {
        try {
            String query = "SELECT * FROM Customers";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("CustomerID") +
                        ", Name: " + rs.getString("FirstName") + " " + rs.getString("LastName") +
                        ", Email: " + rs.getString("Email") + ", Phone: " + rs.getString("Phone") +
                        ", Address: " + rs.getString("Address"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void updateCustomer() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Customer ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter new Email: ");
            String email = sc.nextLine();
            System.out.print("Enter new Phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter new Address: ");
            String address = sc.nextLine();

            String query = "UPDATE Customers SET Email = ?, Phone = ?, Address = ? WHERE CustomerID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Customer updated successfully." : "Customer not found.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void deleteCustomer() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Customer ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            String query = "DELETE FROM Customers WHERE CustomerID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Customer deleted successfully." : "Customer not found.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}

