package com.AssignmentTechshop;

import java.sql.*;
import java.util.Scanner;

public class InventoryService {
    private Connection conn;
    private Scanner scanner;

    public InventoryService(Connection conn) {
        this.conn = conn;
        this.scanner = new Scanner(System.in);
    }

    public void addToInventory() {
        try {
            System.out.print("Enter Product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter Quantity to Add: ");
            int quantity = scanner.nextInt();

            String checkQuery = "SELECT * FROM Inventory WHERE ProductID = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE Inventory SET QuantityInStock = QuantityInStock + ?, LastStockUpdate = NOW() WHERE ProductID = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, productId);
                updateStmt.executeUpdate();
                System.out.println("Inventory updated successfully.");
            } else {
                String insertQuery = "INSERT INTO Inventory (ProductID, QuantityInStock, LastStockUpdate) VALUES (?, ?, NOW())";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setInt(1, productId);
                insertStmt.setInt(2, quantity);
                insertStmt.executeUpdate();
                System.out.println("Inventory added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding to inventory: " + e.getMessage());
        }
    }

    public void removeFromInventory() {
        try {
            System.out.print("Enter Product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter Quantity to Remove: ");
            int quantity = scanner.nextInt();

            String updateQuery = "UPDATE Inventory SET QuantityInStock = QuantityInStock - ?, LastStockUpdate = NOW() WHERE ProductID = ? AND QuantityInStock >= ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, productId);
            updateStmt.setInt(3, quantity);
            int affectedRows = updateStmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Inventory updated successfully.");
            } else {
                System.out.println("Insufficient stock or invalid product.");
            }
        } catch (Exception e) {
            System.out.println("Error removing from inventory: " + e.getMessage());
        }
    }

    public void updateStockQuantity() {
        try {
            System.out.print("Enter Product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter New Quantity: ");
            int quantity = scanner.nextInt();

            String updateQuery = "UPDATE Inventory SET QuantityInStock = ?, LastStockUpdate = NOW() WHERE ProductID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, productId);
            int affected = updateStmt.executeUpdate();
            if (affected > 0)
                System.out.println("Inventory quantity updated.");
            else
                System.out.println("Product not found in inventory.");
        } catch (Exception e) {
            System.out.println("Error updating quantity: " + e.getMessage());
        }
    }

    public void isProductAvailable() {
        try {
            System.out.print("Enter Product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter Quantity to Check: ");
            int quantity = scanner.nextInt();

            String query = "SELECT QuantityInStock FROM Inventory WHERE ProductID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("QuantityInStock");
                if (stock >= quantity)
                    System.out.println("Product is available.");
                else
                    System.out.println("Insufficient stock.");
            } else {
                System.out.println("Product not found in inventory.");
            }
        } catch (Exception e) {
            System.out.println("Error checking product availability: " + e.getMessage());
        }
    }

    public void getInventoryValue() {
        try {
            String query = "SELECT SUM(p.Price * i.QuantityInStock) AS TotalValue " +
                           "FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Total Inventory Value: ₹" + rs.getDouble("TotalValue"));
            }
        } catch (Exception e) {
            System.out.println("Error calculating inventory value: " + e.getMessage());
        }
    }

    public void listLowStockProducts() {
        try {
            System.out.print("Enter stock threshold: ");
            int threshold = scanner.nextInt();

            String query = "SELECT p.ProductName, i.QuantityInStock FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID WHERE i.QuantityInStock < ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, threshold);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Low Stock Products:");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName") + " - Quantity: " + rs.getInt("QuantityInStock"));
            }
        } catch (Exception e) {
            System.out.println("Error listing low stock products: " + e.getMessage());
        }
    }

    public void listOutOfStockProducts() {
        try {
            String query = "SELECT p.ProductName FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID WHERE i.QuantityInStock = 0";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Out of Stock Products:");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName"));
            }
        } catch (Exception e) {
            System.out.println("Error listing out of stock products: " + e.getMessage());
        }
    }

    public void listAllProductsInInventory() {
        try {
            String query = "SELECT p.ProductName, i.QuantityInStock FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("All Products in Inventory:");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName") + " - Quantity: " + rs.getInt("QuantityInStock"));
            }
        } catch (Exception e) {
            System.out.println("Error listing all products: " + e.getMessage());
        }
    }
}
