// RestaurantDAOImpl.java in com.foodapp.dao.impl
package com.foodapp.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;
import com.foodapp.DBUtil.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_QUERY = "INSERT INTO restaurant (restaurantId, restaurantName, cuisineType, " +
                                               "deliveryTime, address, ratings, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM restaurant";
    private static final String FETCH_ONE_QUERY = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_QUERY = "UPDATE restaurant SET address = ?, isActive = ? WHERE restaurantId = ?";
    private static final String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_IMAGE_QUERY = 
            "UPDATE restaurant SET imagePath = ? WHERE restaurantId = ?";

    private Connection con;

    public RestaurantDAOImpl() {
        this.con = DBConnection.connect();
    }

    @Override
    public int insert(Restaurant restaurant) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setInt(1, restaurant.getRestaurantId());
            pstmt.setString(2, restaurant.getRestaurantName());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setInt(4, restaurant.getDeliveryTime());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setFloat(6, restaurant.getRatings());
            pstmt.setBoolean(7, restaurant.isActive());
            pstmt.setString(8, restaurant.getImagePath());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Restaurant> fetchAll() {
        List<Restaurant> restaurantList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_QUERY)) {
            while (rs.next()) {
                restaurantList.add(new Restaurant(
                    rs.getInt("restaurantId"),
                    rs.getString("restaurantName"),
                    rs.getString("cuisineType"),
                    rs.getInt("deliveryTime"),
                    rs.getString("address"),
                    rs.getFloat("ratings"),
                    rs.getBoolean("isActive"),
                    rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant fetchOne(int restaurantId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE_QUERY)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Restaurant(
                        rs.getInt("restaurantId"),
                        rs.getString("restaurantName"),
                        rs.getString("cuisineType"),
                        rs.getInt("deliveryTime"),
                        rs.getString("address"),
                        rs.getFloat("ratings"),
                        rs.getBoolean("isActive"),
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
    public int update(int restaurantId, String address, boolean isActive) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, address);
            pstmt.setBoolean(2, isActive);
            pstmt.setInt(3, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int restaurantId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int updateImagePath(int restaurantId, String imagePath) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_IMAGE_QUERY)) {
            pstmt.setString(1, imagePath);
            pstmt.setInt(2, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
