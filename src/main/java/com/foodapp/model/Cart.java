package com.foodapp.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItems> items;
    private int restaurantId;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(CartItems item) {
        if (items.isEmpty()) {
            restaurantId = item.getRestaurantId();
        } else if (restaurantId != item.getRestaurantId()) {
            throw new IllegalStateException("Cannot add items from different restaurants");
        }

        int itemId = item.getItemId();
        if (items.containsKey(itemId)) {
            CartItems existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            items.put(itemId, item);
        }
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                removeItem(itemId);
            } else {
                CartItems existingItem = items.get(itemId);
                existingItem.setQuantity(quantity);
            }
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
        if (items.isEmpty()) {
            restaurantId = 0;
        }
    }

    public void clear() {
        items.clear();
        restaurantId = 0;
    }

    public Map<Integer, CartItems> getItems() {
        return items;
    }

    public double getTotalAmount() {
        double total = 0;
        for (CartItems item : items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
}
