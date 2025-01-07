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
        try {
            String restaurantIdParam = request.getParameter("restaurantId");
            if (restaurantIdParam == null || !restaurantIdParam.matches("\\d+")) {
                request.setAttribute("error", "Invalid restaurant ID");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                return;
            }
            
            int restaurantId = Integer.parseInt(restaurantIdParam);
            HttpSession session = request.getSession(false);
            
            List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
            if (restaurantList == null) {
                request.setAttribute("error", "Unable to fetch restaurant information");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                return;
            }
            
            Restaurant currentRestaurant = restaurantList.stream()
                    .filter(r -> r.getRestaurantId() == restaurantId)
                    .findFirst()
                    .orElse(null);
                    
            if (currentRestaurant == null) {
                request.setAttribute("error", "Restaurant not found");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                return;
            }
            
            List<Menu> menuItems = menuDAO.fetchByRestaurantId(restaurantId);
            if (menuItems == null || menuItems.isEmpty()) {
                menuItems = new ArrayList<>();
                request.setAttribute("error", "No menu items available for this restaurant.");
            }
            
            request.setAttribute("menuItems", menuItems);
            request.setAttribute("restaurant", currentRestaurant);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching the menu");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }
}