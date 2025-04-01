package com.techshop;

public class OrderDetail {
    private int orderDetailID;
    private Order order;
    private Product product;
    private int quantity;

    // Constructor
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        setQuantity(quantity);
        order.calculateTotalAmount(product.getPrice() * quantity);
    }

    // Getters
    public int getOrderDetailID() { return orderDetailID; }
    public Order getOrder() { return order; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    // Setter with validation
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity must be greater than zero.");
        }
    }

    // Methods
    public void getOrderDetailInfo() {
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductName() + ", Quantity: " + quantity);
    }
}
