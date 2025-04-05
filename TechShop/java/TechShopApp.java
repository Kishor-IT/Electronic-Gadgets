package com.AssignmentTechshop;

import java.sql.Connection;
import java.util.Scanner;



public class TechShopApp {
    public static void main(String[] args) {
        try (Connection conn = DbConnection.getConnection(); Scanner scanner = new Scanner(System.in)) {
            CustomerService customerService = new CustomerService(conn);
            ProductService productService = new ProductService(conn);
            OrderService orderService = new OrderService(conn);
            InventoryService inventoryService = new InventoryService(conn);
            OrderService orderDetailService = new OrderService(conn);

            while (true) {
                System.out.println("\n--- TechShop Main Menu ---");
                System.out.println("1. Customer Management");
                System.out.println("2. Product Management");
                System.out.println("3. Order Management");
                System.out.println("4. Inventory Management");
                System.out.println("5. Order Detail Management");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        customerMenu(customerService, scanner);
                        break;
                    case 2:
                        productMenu(productService, scanner);
                        break;
                    case 3:
                        orderMenu(orderService, scanner);
                        break;
                    case 4:
                        inventoryMenu(inventoryService, scanner);
                        break;
                    case 5:
                        orderDetailMenu(orderService, scanner);
                        break;
                    case 6:
                        System.out.println("Thank you for using TechShop. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Customer Menu
    private static void customerMenu(CustomerService cs, Scanner scanner) {
        System.out.println("\n--- Customer Menu ---");
        System.out.println("1. Register Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. View All Customers");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: cs.addCustomer(); break;
            case 2: cs.updateCustomer(); break;
            case 3: cs.deleteCustomer(); break;
            case 4: cs.viewCustomers(); break;
            default: System.out.println("Invalid option.");
        }
    }

    // Product Menu
    private static void productMenu(ProductService ps, Scanner scanner) {
        System.out.println("\n--- Product Menu ---");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. List Products");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: ps.addProduct(); break;
            case 2: ps.updateProduct(); break;
            case 3: ps.deleteProduct(); break;
            case 4: ps.viewProducts(); break;
            default: System.out.println("Invalid option.");
        }
    }

    // Order Menu
    private static void orderMenu(OrderService os, Scanner scanner) {
        System.out.println("\n--- Order Menu ---");
        System.out.println("1. Create Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("4. List Orders");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: os.addOrderDetail(); break;
            case 2: os.updateQuantity(); break;
            case 3: os.applyDiscount(); break;
            case 4: os.listOrderDetailsByOrderId(); break;
            default: System.out.println("Invalid option.");
        }
    }

    // Inventory Menu
    private static void inventoryMenu(InventoryService is, Scanner scanner) {
        System.out.println("\n--- Inventory Menu ---");
        System.out.println("1. Add Inventory");
        System.out.println("2. Add to Inventory Quantity");
        System.out.println("3. Remove from Inventory");
        System.out.println("4. Update Stock Quantity");
        System.out.println("5. Check Product Availability");
        System.out.println("6. Calculate Inventory Value");
        System.out.println("7. List Low Stock Products");
        System.out.println("8. List Out Of Stock Products");
        System.out.println("9. List All Products in Inventory");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            
            case 1: is.addToInventory(); break;
            case 2: is.removeFromInventory(); break;
            case 3: is.updateStockQuantity(); break;
            case 4: is.isProductAvailable(); break;
            case 5: is.listLowStockProducts(); break;
            case 6: is.listOutOfStockProducts(); break;
            case 7: is.listOutOfStockProducts(); break;
            default: System.out.println("Invalid option.");
        }
    }

    // Order Detail Menu
    private static void orderDetailMenu(OrderService ods, Scanner scanner) {
        System.out.println("\n--- Order Detail Menu ---");
        System.out.println("1. Add Order Detail");
        System.out.println("2. Update Quantity");
        System.out.println("3. Apply Discount");
        System.out.println("4. List Order Details by Order ID");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: ods.addOrderDetail(); break;
            case 2: ods.updateQuantity(); break;
            case 3: ods.applyDiscount(); break;
            case 4: ods.listOrderDetailsByOrderId(); break;
            default: System.out.println("Invalid option.");
        }
    }
}
