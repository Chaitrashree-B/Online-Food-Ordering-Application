package com.foodapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.foodapp.model.Restaurant;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/search")
public class RestaurantSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String query = request.getParameter("query").toLowerCase();
        List<Restaurant> allRestaurants = (List<Restaurant>) request.getSession().getAttribute("restaurantList");
        
        List<Restaurant> searchResults = allRestaurants.stream()
            .filter(restaurant -> 
                restaurant.getRestaurantName().toLowerCase().contains(query) ||
                restaurant.getAddress().toLowerCase().contains(query))
            .collect(Collectors.toList());

        request.setAttribute("restaurantList", searchResults);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}