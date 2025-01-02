package com.foodapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.foodapp.dao.MenuDAO;
import com.foodapp.daoImpl.MenuDAOImpl;
import com.foodapp.model.Menu;
import com.foodapp.model.Restaurant;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user session exists
        if (session == null || session.getAttribute("User") == null) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            // Get and validate restaurantId parameter
            String restaurantIdParam = request.getParameter("restaurantId");
            if (restaurantIdParam == null || !restaurantIdParam.matches("\\d+")) {
                request.setAttribute("error", "Invalid restaurant ID");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                return;
            }
            
            int restaurantId = Integer.parseInt(restaurantIdParam);

            // Get restaurant list from session
            List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
            if (restaurantList == null) {
                // Try to fetch restaurant list if not in session
                // You'll need to implement this method in your DAO
                // restaurantList = restaurantDAO.getAllRestaurants();
                if (restaurantList == null) {
                    request.setAttribute("error", "Unable to fetch restaurant information");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                    return;
                }
            }

            // Find the current restaurant
            Restaurant currentRestaurant = restaurantList.stream()
                    .filter(r -> r.getRestaurantId() == restaurantId)
                    .findFirst()
                    .orElse(null);

            if (currentRestaurant == null) {
                request.setAttribute("error", "Restaurant not found");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                return;
            }

            // Fetch menu items for the restaurant
            List<Menu> menuItems = menuDAO.fetchByRestaurantId(restaurantId);

            // Check if menu items are available
            if (menuItems == null || menuItems.isEmpty()) {
                menuItems = new ArrayList<>(); // Initialize empty list instead of null
                request.setAttribute("error", "No menu items available for this restaurant.");
            }

            // Set attributes for JSP
            request.setAttribute("menuItems", menuItems);
            request.setAttribute("restaurant", currentRestaurant);

            // Forward to menu.jsp
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching the menu");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }
}