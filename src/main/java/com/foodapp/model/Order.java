// Order.java in com.foodapp.model
package com.foodapp.model;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private float totalAmount;
    private String status;
    private String paymentMode;

    // No-argument constructor
    public Order() {
        super();
    }

    // Parameterized constructor
    public Order(int orderId, int userId, int restaurantId, float totalAmount, String status, String paymentMode) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }
    public Order( int userId, int restaurantId, float totalAmount, String status, String paymentMode) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }
    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", User ID: " + userId + ", Restaurant ID: " + restaurantId + 
               ", Total Amount: " + totalAmount + ", Status: " + status + ", Payment Mode: " + paymentMode;
    }
}
