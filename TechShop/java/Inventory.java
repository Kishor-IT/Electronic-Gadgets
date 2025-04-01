package com.techshop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory {
    private int inventoryID;
    private Product product;
    private int quantityInStock;
    private Date lastStockUpdate;
    
    public Inventory(int inventoryID, Product product, int quantityInStock) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = Math.max(quantityInStock, 0); // Ensure non-negative
        this.lastStockUpdate = new Date();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void addToInventory(int quantity) {
        if (quantity > 0) {
            this.quantityInStock += quantity;
            this.lastStockUpdate = new Date();
        }
    }

    public void removeFromInventory(int quantity) {
        if (quantity > 0 && quantity <= this.quantityInStock) {
            this.quantityInStock -= quantity;
            this.lastStockUpdate = new Date();
        }
    }

    public void updateStockQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
            this.lastStockUpdate = new Date();
        }
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return this.quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return this.quantityInStock * this.product.getPrice();
    }

    public static List<Product> listLowStockProducts(List<Inventory> inventoryList, int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Inventory inventory : inventoryList) {
            if (inventory.getQuantityInStock() < threshold) {
                lowStockProducts.add(inventory.getProduct());
            }
        }
        return lowStockProducts;
    }

    public static List<Product> listOutOfStockProducts(List<Inventory> inventoryList) {
        List<Product> outOfStockProducts = new ArrayList<>();
        for (Inventory inventory : inventoryList) {
            if (inventory.getQuantityInStock() == 0) {
                outOfStockProducts.add(inventory.getProduct());
            }
        }
        return outOfStockProducts;
    }

    public static void listAllProducts(List<Inventory> inventoryList) {
        for (Inventory inventory : inventoryList) {
            System.out.println("Product: " + inventory.getProduct().getProductName() + ", Quantity: " + inventory.getQuantityInStock());
        }
    }
}
