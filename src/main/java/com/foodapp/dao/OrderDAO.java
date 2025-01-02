// OrderDAO.java in com.foodapp.dao
package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Order;

public interface OrderDAO {
    int insert(Order order);
    List<Order> fetchAll();
    Order fetchOne(int orderId);
    List<Order> fetchByUserId(int userId);
    List<Order> fetchByRestaurantId(int restaurantId);
    int update(int orderId, String status, String paymentMode);
    int delete(int orderId);
}
