// OrderItem.java in com.foodapp.model
package com.foodapp.model;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int menuId;
    private int quantity;
    private int itemTotal;

    // No-argument constructor
    public OrderItem() {
        super();
    }

    // Parameterized constructor
    public OrderItem(int orderItemId, int orderId, int menuId, int quantity, int itemTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }
    public OrderItem(int orderId, int menuId, int quantity, int itemTotal) {

        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }


    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public String toString() {
        return "OrderItem ID: " + orderItemId + ", Order ID: " + orderId + ", Menu ID: " + menuId + 
               ", Quantity: " + quantity + ", Item Total: " + itemTotal;
    }
}
