package com.foodapp.controller;

import java.io.IOException;
import com.foodapp.dao.MenuDAO;
import com.foodapp.daoImpl.MenuDAOImpl;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItems;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        MenuDAO menuDAO = new MenuDAOImpl(); // Initialize menuDAO

        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            Menu menuItem = menuDAO.fetchOne(itemId); // Fetch the menu item details
            int quantity=1;
            switch (action) {
                case "add":
                    if (menuItem != null) {
                        CartItems cartItem = new CartItems(
                                menuItem.getMenuId(),
                                menuItem.getRestaurantId(),
                                menuItem.getMenuName(),
                                quantity,
                                menuItem.getPrice()
                        );
                        cart.addItem(cartItem);
                    }
                    break;

                case "update":
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    cart.updateItem(itemId, quantity);
                    break;

                case "remove":
                    cart.removeItem(itemId);
                    break;

                case "clear":
                    cart.clear();
                    break;
            }

            // Redirect to cart.jsp and pass the restaurantId for context
            int restaurantId = cart.getRestaurantId();
            response.sendRedirect("cart.jsp?restaurantId=" + restaurantId); // Pass restaurantId in query params

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
