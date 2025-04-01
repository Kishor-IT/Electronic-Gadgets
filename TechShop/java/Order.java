package com.techshop;
import java.util.Date;

public class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String status;

    // Constructor
    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = new Date();
        this.totalAmount = 0;
        this.status = "Processing";
        customer.incrementTotalOrders();  // Updating total orders for the customer
    }

    // Getters
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    // Methods
    public void calculateTotalAmount(double amount) {
        if (amount >= 0) {
            this.totalAmount += amount;
        }
    }

    public void updateOrderStatus(String newStatus) {
        this.status = newStatus;
    }

    public void getOrderDetails() {
        System.out.println("Order ID: " + orderID + ", Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Order Date: " + orderDate + ", Status: " + status);
        System.out.println("Total Amount: $" + totalAmount);
    }
}
