// Restaurant.java in com.foodapp.model
package com.foodapp.model;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private float ratings;
    private boolean isActive;
    private String imagePath;

    // No-argument constructor
    public Restaurant() {
        super();
    }

    // Parameterized constructor
    public Restaurant(int restaurantId, String restaurantName, String cuisineType, int deliveryTime, 
                      String address, float ratings, boolean isActive, String imagePath) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.ratings = ratings;
        this.isActive = isActive;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Restaurant ID: " + restaurantId + ", Name: " + restaurantName + ", Cuisine: " + cuisineType +
               ", Delivery Time: " + deliveryTime + " mins, Address: " + address + ", Ratings: " + ratings +
               ", Active: " + isActive + ", Image Path: " + imagePath;
    }
}
