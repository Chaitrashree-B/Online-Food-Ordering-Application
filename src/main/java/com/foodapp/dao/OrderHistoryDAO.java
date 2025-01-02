package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.OrderHistory;

public interface OrderHistoryDAO {
    int insert(OrderHistory orderHistory);
    List<OrderHistory> fetchAll();
    OrderHistory fetchOne(int orderHistoryId);
    List<OrderHistory> fetchByUserId(int usersId);
    List<OrderHistory> fetchByOrderId(int ordersId);
    int update(int orderHistoryId, String status);
    int delete(int orderHistoryId);
}
