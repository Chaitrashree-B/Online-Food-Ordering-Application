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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        MenuDAO menuDAO = new MenuDAOImpl();

        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            Menu menuItem = menuDAO.fetchOne(itemId);
            int quantity = 1;

            switch (action) {
                case "add":
                    if (menuItem != null) {
                        int newRestaurantId = menuItem.getRestaurantId();

                        // Check if the cart contains items from a different restaurant
                        if (!cart.getItems().isEmpty() && 
                            cart.getItems().values().iterator().next().getRestaurantId() != newRestaurantId) {
                            // Clear the cart if restaurant IDs differ
                            cart.clear();
                            session.setAttribute("cartItemCount", 0);
                        }

                        CartItems cartItem = new CartItems(
                                menuItem.getMenuId(),
                                menuItem.getRestaurantId(),
                                menuItem.getMenuName(),
                                quantity,
                                menuItem.getPrice()
                        );

                        cart.addItem(cartItem);
                        session.setAttribute("cartSuccess", "Item added to cart successfully!");
                    } else {
                        session.setAttribute("cartError", "Menu item not found.");
                    }

                    // Redirect to cart.jsp after adding an item
                    session.setAttribute("cartItemCount", cart.getItems().size());
                    session.setAttribute("cart", cart);
                    response.sendRedirect("cart.jsp");
                    return;

                case "update":
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    if (quantity <= 0) {
                        cart.removeItem(itemId);
                    } else {
                        cart.updateItem(itemId, quantity);
                    }
                    break;

                case "remove":
                    cart.removeItem(itemId);
                    break;

                case "clear":
                    cart.clear();
                    break;

                default:
                    session.setAttribute("cartError", "Invalid action.");
                    break;
            }

            // Update cart item count in session
            session.setAttribute("cartItemCount", cart.getItems().size());
            session.setAttribute("cart", cart);

            // Redirect to cart.jsp
            response.sendRedirect("cart.jsp");

        } catch (NumberFormatException e) {
            session.setAttribute("cartError", "Invalid item ID.");
            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("cartError", "An error occurred while processing your request.");
            response.sendRedirect("cart.jsp");
        }
    }
}
