package com.foodapp.controller;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.daoImpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;
import com.foodapp.model.User;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class GetProducts
 */
@WebServlet("/GetAllRestaurants")
public class GetAllRestaurants extends HttpServlet {
    private List<Restaurant> restaurantList;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if user is logged in
        User user = (User) session.getAttribute("User");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Fetch restaurants from the database
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        restaurantList = restaurantDAO.fetchAll();

        // Set image paths (if not already set in the database)
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getImagePath() == null || restaurant.getImagePath().isEmpty()) {
                // Set a default image path if missing
                restaurant.setImagePath("restaurant_images/default.jpg");
            }
        }

        // Set restaurant list in session
        session.setAttribute("restaurantList", restaurantList);

        // Redirect to home.jsp
        resp.sendRedirect("home.jsp");
    }
}
