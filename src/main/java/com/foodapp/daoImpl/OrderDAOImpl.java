// OrderDAOImpl.java in com.foodapp.dao.impl
package com.foodapp.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.dao.OrderDAO;
import com.foodapp.model.Order;
import com.foodapp.DBUtil.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_QUERY = "INSERT INTO orders (user_id, restaurant_id, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM orders";
    private static final String FETCH_ONE_QUERY = "SELECT * FROM orders WHERE orderId = ?";
    private static final String FETCH_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String FETCH_BY_RESTAURANT_ID_QUERY = "SELECT * FROM orders WHERE restaurant_id = ?";
    private static final String UPDATE_QUERY = "UPDATE orders SET status = ?, paymentMode = ? WHERE orderId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE orderId = ?";

    private Connection con;
	private int orderId;

    public OrderDAOImpl() {
        this.con = DBConnection.connect();
    }

    @Override
    public int insert(Order order) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setFloat(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                    	orderId=rs.getInt(1);
                        order.setOrderId(orderId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public List<Order> fetchAll() {
        List<Order> orderList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_QUERY)) {
            while (rs.next()) {
                orderList.add(new Order(
                    rs.getInt("orderId"),
                    rs.getInt("user_id"),
                    rs.getInt("restaurant_id"),
                    rs.getFloat("totalAmount"),
                    rs.getString("status"),
                    rs.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order fetchOne(int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE_QUERY)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                        rs.getInt("orderId"),
                        rs.getInt("user_id"),
                        rs.getInt("restaurant_id"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> fetchByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_USER_ID_QUERY)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderList.add(new Order(
                        rs.getInt("orderId"),
                        rs.getInt("user_id"),
                        rs.getInt("restaurant_id"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> fetchByRestaurantId(int restaurantId) {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_RESTAURANT_ID_QUERY)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderList.add(new Order(
                        rs.getInt("orderId"),
                        rs.getInt("user_id"),
                        rs.getInt("restaurant_id"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int update(int orderId, String status, String paymentMode) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, status);
            pstmt.setString(2, paymentMode);
            pstmt.setInt(3, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
