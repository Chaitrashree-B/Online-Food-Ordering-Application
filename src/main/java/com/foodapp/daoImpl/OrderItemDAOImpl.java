// OrderItemDAOImpl.java in com.foodapp.dao.impl
package com.foodapp.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;
import com.foodapp.DBUtil.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_QUERY = "INSERT INTO orderitems (order_id, menu_id, quantity, itemTotal) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM orderitems";
    private static final String FETCH_ONE_QUERY = "SELECT * FROM orderitems WHERE orderItemId = ?";
    private static final String FETCH_BY_ORDER_ID_QUERY = "SELECT * FROM orderitems WHERE order_id = ?";
    private static final String FETCH_BY_MENU_ID_QUERY = "SELECT * FROM orderitems WHERE menu_id = ?";
    private static final String UPDATE_QUERY = "UPDATE orderitems SET quantity = ?, itemTotal = ? WHERE orderItemId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderitems WHERE orderItemId = ?";

    private Connection con;

    public OrderItemDAOImpl() {
        this.con = DBConnection.connect();
    }

    @Override
    public int insert(OrderItem orderItem) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setInt(4, orderItem.getItemTotal());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderItem> fetchAll() {
        List<OrderItem> orderItemList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_QUERY)) {
            while (rs.next()) {
                orderItemList.add(new OrderItem(
                    rs.getInt("orderItemId"),
                    rs.getInt("order_id"),
                    rs.getInt("menu_id"),
                    rs.getInt("quantity"),
                    rs.getInt("itemTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem fetchOne(int orderItemId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE_QUERY)) {
            pstmt.setInt(1, orderItemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("order_id"),
                        rs.getInt("menu_id"),
                        rs.getInt("quantity"),
                        rs.getInt("itemTotal")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> fetchByOrderId(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ORDER_ID_QUERY)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderItemList.add(new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("order_id"),
                        rs.getInt("menu_id"),
                        rs.getInt("quantity"),
                        rs.getInt("itemTotal")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public List<OrderItem> fetchByMenuId(int menuId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_MENU_ID_QUERY)) {
            pstmt.setInt(1, menuId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderItemList.add(new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("order_id"),
                        rs.getInt("menu_id"),
                        rs.getInt("quantity"),
                        rs.getInt("itemTotal")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public int update(int orderItemId, int quantity, int itemTotal) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, itemTotal);
            pstmt.setInt(3, orderItemId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int orderItemId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, orderItemId);
            return pstmt.executeUpdate();
        } catch (SQLException e)            {
            e.printStackTrace();
        }
        return 0;
    }
}
