package com.techshop;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private boolean inStock;

    // Constructor
    public Product(int productID, String productName, String description, double price, boolean inStock) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        setPrice(price);
        this.inStock = inStock;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public boolean isInStock() { return inStock; }

    // Setters with validation
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public void updateStock(boolean stockStatus) {
        this.inStock = stockStatus;
    }

    // Methods
    public void getProductDetails() {
        System.out.println("Product ID: " + productID + ", Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price + ", In Stock: " + (inStock ? "Yes" : "No"));
    }
}
