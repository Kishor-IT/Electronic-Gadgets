package com.AssignmentTechshop;

import java.sql.*;
import java.util.Scanner;

public class ProductService {
    private Connection conn;

    public ProductService(Connection conn) {
        this.conn = conn;
    }

    public void addProduct() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.print("Enter description: ");
            String desc = sc.nextLine();
            System.out.print("Enter price: ");
            double price = sc.nextDouble();

            String sql = "INSERT INTO Products (ProductName, Description, Price) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, desc);
                pstmt.setDouble(3, price);
                pstmt.executeUpdate();
                System.out.println("Product added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public void viewProducts() {
        String sql = "SELECT * FROM Products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ProductID") + ", Name: " + rs.getString("ProductName") +
                        ", Description: " + rs.getString("Description") + ", Price: " + rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
    }

    public void updateProduct() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Product ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new product name: ");
            String name = sc.nextLine();
            System.out.print("Enter new description: ");
            String desc = sc.nextLine();
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();

            String sql = "UPDATE Products SET ProductName=?, Description=?, Price=? WHERE ProductID=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, desc);
                pstmt.setDouble(3, price);
                pstmt.setInt(4, id);
                int rows = pstmt.executeUpdate();
                if (rows > 0) System.out.println("Product updated successfully.");
                else System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM Products WHERE ProductID=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rows = pstmt.executeUpdate();
                if (rows > 0) System.out.println("Product deleted successfully.");
                else System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
}

