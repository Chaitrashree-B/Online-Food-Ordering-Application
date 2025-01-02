/*package com.foodapp.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.model.OrderHistory;
import com.foodapp.DBUtil.DBConnection;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private static final String INSERT_QUERY = "INSERT INTO orderHistory (orderHistoryId, ordersid, usersid, totalAmount, status) VALUES (?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM orderHistory";
    private static final String FETCH_ONE_QUERY = "SELECT * FROM orderHistory WHERE orderHistoryId = ?";
    private static final String FETCH_BY_USERID_QUERY = "SELECT * FROM orderHistory WHERE usersid = ?";
    private static final String FETCH_BY_ORDERID_QUERY = "SELECT * FROM orderHistory WHERE ordersid = ?";
    private static final String UPDATE_QUERY = "UPDATE orderHistory SET status = ? WHERE orderHistoryId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderHistory WHERE orderHistoryId = ?";

    private Connection con;

    public OrderHistoryDAOImpl() {
        this.con = DBConnection.connect();
    }

    @Override
    public int insert(OrderHistory orderHistory) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setInt(1, orderHistory.getOrderHistoryId());
            pstmt.setInt(2, orderHistory.getOrdersId());
            pstmt.setInt(3, orderHistory.getUsersId());
            pstmt.setFloat(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderHistory> fetchAll() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_QUERY)) {
            while (rs.next()) {
                orderHistoryList.add(new OrderHistory(
                    rs.getInt("orderHistoryId"),
                    rs.getInt("ordersid"),
                    rs.getInt("usersid"),
                    rs.getFloat("totalAmount"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory fetchOne(int orderHistoryId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE_QUERY)) {
            pstmt.setInt(1, orderHistoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderHistory(
                        rs.getInt("orderHistoryId"),
                        rs.getInt("ordersid"),
                        rs.getInt("usersid"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderHistory> fetchByUserId(int usersId) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_USERID_QUERY)) {
            pstmt.setInt(1, usersId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderHistoryList.add(new OrderHistory(
                        rs.getInt("orderHistoryId"),
                        rs.getInt("ordersid"),
                        rs.getInt("usersid"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public List<OrderHistory> fetchByOrderId(int ordersId) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ORDERID_QUERY)) {
            pstmt.setInt(1, ordersId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderHistoryList.add(new OrderHistory(
                        rs.getInt("orderHistoryId"),
                        rs.getInt("ordersid"),
                        rs.getInt("usersid"),
                        rs.getFloat("totalAmount"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public int update(int orderHistoryId, String status) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderHistoryId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int orderHistoryId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, orderHistoryId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}*/
