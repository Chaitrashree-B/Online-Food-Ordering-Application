// OrderItemDAO.java in com.foodapp.dao
package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.OrderItem;

public interface OrderItemDAO {
    int insert(OrderItem orderItem);
    List<OrderItem> fetchAll();
    OrderItem fetchOne(int orderItemId);
    List<OrderItem> fetchByOrderId(int orderId);
    List<OrderItem> fetchByMenuId(int menuId);
    int update(int orderItemId, int quantity, int itemTotal);
    int delete(int orderItemId);
}
