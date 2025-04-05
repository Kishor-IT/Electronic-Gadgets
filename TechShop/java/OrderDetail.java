package com.AssignmentTechshop;

import java.sql.*;
import java.util.Scanner;

public class OrderService {
    private Connection conn;
    private Scanner scanner;

    public OrderService(Connection conn) {
        this.conn = conn;
        this.scanner = new Scanner(System.in);
    }

    public void addOrderDetail() {
        try {
            System.out.print("Enter Order ID: ");
            int orderId = scanner.nextInt();

            System.out.print("Enter Product ID: ");
            int productId = scanner.nextInt();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            // Check product price
            String productQuery = "SELECT Price FROM Products WHERE ProductID = ?";
            PreparedStatement productStmt = conn.prepareStatement(productQuery);
            productStmt.setInt(1, productId);
            ResultSet productRs = productStmt.executeQuery();

            if (!productRs.next()) {
                System.out.println("Product not found.");
                return;
            }

            double price = productRs.getDouble("Price");
            double subtotal = price * quantity;

            String insertQuery = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, subtotal);
            stmt.executeUpdate();

            System.out.println("Order detail added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding order detail: " + e.getMessage());
        }
    }

    public void updateQuantity() {
        try {
            System.out.print("Enter Order Detail ID: ");
            int orderDetailId = scanner.nextInt();

            System.out.print("Enter New Quantity: ");
            int quantity = scanner.nextInt();

            String updateQuery = "UPDATE OrderDetails od JOIN Products p ON od.ProductID = p.ProductID " +
                                 "SET od.Quantity = ?, od.Subtotal = ? * p.Price WHERE od.OrderDetailID = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setInt(1, quantity);
            stmt.setInt(2, quantity);
            stmt.setInt(3, orderDetailId);
            int affected = stmt.executeUpdate();

            if (affected > 0)
                System.out.println("Quantity updated successfully.");
            else
                System.out.println("Order detail not found.");
        } catch (Exception e) {
            System.out.println("Error updating quantity: " + e.getMessage());
        }
    }

    public void applyDiscount() {
        try {
            System.out.print("Enter Order Detail ID: ");
            int orderDetailId = scanner.nextInt();

            System.out.print("Enter Discount Percentage: ");
            double discount = scanner.nextDouble();

            String updateQuery = "UPDATE OrderDetails od JOIN Products p ON od.ProductID = p.ProductID " +
                                 "SET od.Subtotal = (od.Quantity * p.Price) * (1 - ? / 100) WHERE od.OrderDetailID = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setDouble(1, discount);
            stmt.setInt(2, orderDetailId);
            int affected = stmt.executeUpdate();

            if (affected > 0)
                System.out.println("Discount applied successfully.");
            else
                System.out.println("Order detail not found.");
        } catch (Exception e) {
            System.out.println("Error applying discount: " + e.getMessage());
        }
    }

    public void listOrderDetailsByOrderId() {
        try {
            System.out.print("Enter Order ID: ");
            int orderId = scanner.nextInt();

            String query = "SELECT od.OrderDetailID, p.ProductName, od.Quantity, od.Subtotal " +
                           "FROM OrderDetails od JOIN Products p ON od.ProductID = p.ProductID WHERE od.OrderID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Order Details:");
            while (rs.next()) {
                System.out.println("OrderDetailID: " + rs.getInt("OrderDetailID") +
                        ", Product: " + rs.getString("ProductName") +
                        ", Quantity: " + rs.getInt("Quantity") +
                        ", Subtotal: ₹" + rs.getDouble("Subtotal"));
            }
        } catch (Exception e) {
            System.out.println("Error listing order details: " + e.getMessage());
        }
    }
}
