// RestaurantDAO.java in com.foodapp.dao
package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Restaurant;

public interface RestaurantDAO {
    int insert(Restaurant restaurant);
    List<Restaurant> fetchAll();
    Restaurant fetchOne(int restaurantId);
    int update(int restaurantId, String address, boolean isActive);
    int delete(int restaurantId);
    int updateImagePath(int restaurantId, String imagePath);
}
