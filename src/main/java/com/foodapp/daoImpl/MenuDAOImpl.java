// MenuDAOImpl.java in com.foodapp.dao.impl
package com.foodapp.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;
import com.foodapp.DBUtil.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_QUERY = "INSERT INTO menu (menuId, restaurantId, menuName, description, price, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM menu";
    private static final String FETCH_ONE_QUERY = "SELECT * FROM menu WHERE menuId = ?";
    private static final String FETCH_BY_RESTAURANT_ID_QUERY = "SELECT * FROM menu WHERE restaurantId = ?";
    private static final String UPDATE_QUERY = "UPDATE menu SET description = ?, price = ?, isAvailable = ? WHERE menuId = ?";
    private static final String DELETE_QUERY = "DELETE FROM menu WHERE menuId = ?";

    private Connection con;

    public MenuDAOImpl() {
        this.con = DBConnection.connect();
    }

    @Override
    public int insert(Menu menu) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setInt(1, menu.getMenuId());
            pstmt.setInt(2, menu.getRestaurantId());
            pstmt.setString(3, menu.getMenuName());
            pstmt.setString(4, menu.getDescription());
            pstmt.setInt(5, menu.getPrice());
            pstmt.setBoolean(6, menu.isAvailable());
            pstmt.setString(7, menu.getImagePath());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Menu> fetchAll() {
        List<Menu> menuList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_QUERY)) {
            while (rs.next()) {
                menuList.add(new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restaurantId"),
                    rs.getString("menuName"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu fetchOne(int menuId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE_QUERY)) {
            pstmt.setInt(1, menuId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("menuName"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getBoolean("isAvailable"),
                        rs.getString("imagePath")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> fetchByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_RESTAURANT_ID_QUERY)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    menuList.add(new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("menuName"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getBoolean("isAvailable"),
                        rs.getString("imagePath")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public int update(int menuId, String description, int price, boolean isAvailable) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, description);
            pstmt.setInt(2, price);
            pstmt.setBoolean(3, isAvailable);
            pstmt.setInt(4, menuId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int menuId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, menuId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
