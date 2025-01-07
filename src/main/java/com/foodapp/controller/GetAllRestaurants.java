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

@WebServlet("/GetAllRestaurants")
public class GetAllRestaurants extends HttpServlet {
    private List<Restaurant> restaurantList;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Get existing session if it exists, don't create new one
        HttpSession session = req.getSession(true);

        try {
            // Only fetch restaurants if they're not already in session
            if (session.getAttribute("restaurantList") == null) {
                RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
                restaurantList = restaurantDAO.fetchAll();

                // Set image paths
                for (Restaurant restaurant : restaurantList) {
                    if (restaurant.getImagePath() == null || restaurant.getImagePath().isEmpty()) {
                        restaurant.setImagePath("restaurant_images/default.jpg");
                    }
                }

                // Set restaurant list in session
                session.setAttribute("restaurantList", restaurantList);
            }

            // Forward to home.jsp
            req.getRequestDispatcher("home.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to load restaurants");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}