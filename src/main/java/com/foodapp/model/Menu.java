// Menu.java in com.foodapp.model
package com.foodapp.model;

public class Menu {
    private int menuId;
    private int restaurantId;
    private String menuName;
    private String description;
    private int price;
    private boolean isAvailable;
    private String imagePath;

    // No-argument constructor
    public Menu() {
        super();
    }

    // Parameterized constructor
    public Menu(int menuId, int restaurantId, String menuName, String description, int price, boolean isAvailable, String imagePath) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Menu ID: " + menuId + ", Restaurant ID: " + restaurantId + ", Menu Name: " + menuName + 
               ", Description: " + description + ", Price: " + price + ", Available: " + isAvailable + 
               ", Image Path: " + imagePath;
    }
}
