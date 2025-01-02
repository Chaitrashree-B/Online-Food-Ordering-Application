package com.foodapp.controller;

import java.io.IOException;
import java.util.Map.Entry;

import com.foodapp.dao.OrderDAO;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.daoImpl.OrderDAOImpl;
import com.foodapp.daoImpl.OrderItemDAOImpl;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItems;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO odao;
    private OrderItemDAO oidao;

    public void init() {
        try {
            odao = new OrderDAOImpl();
            oidao = new OrderItemDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("User");

        System.out.println("User is not null");
        System.out.println("Cart is not null");

        if (cart != null && user != null && !cart.getItems().isEmpty()) {
            System.out.println("Inside CheckedOutServlet IF is true");

            String paymentMethod = req.getParameter("paymentMode");

            if (paymentMethod == null || paymentMethod.isEmpty()) {
                req.setAttribute("error", "Please select a payment method");
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                return;
            }

            // Get restaurant ID from the first item in cart
            int restaurantId;
            try {
                CartItems firstItem = cart.getItems().values().iterator().next();
                restaurantId = firstItem.getRestaurantId();
            } catch (Exception e) {
                req.setAttribute("error", "Invalid cart data");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                return;
            }

            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId(restaurantId);
            order.setPaymentMode(paymentMethod);
            order.setStatus("Pending");

            float totalAmount = 0.0f;
            for (CartItems item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            order.setTotalAmount(totalAmount);
            int orderId = odao.insert(order);

            if (orderId > 0) {
                // Insert each cart item into the order_items table
                for (Entry<Integer, CartItems> entry : cart.getItems().entrySet()) {
                    CartItems cartItem = entry.getValue();
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(cartItem.getItemId());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setItemTotal((int) cartItem.getPrice() * cartItem.getQuantity());

                    int result = oidao.insert(orderItem);
                    System.out.println("OrderItem insert result: " + result);
                }

                session.removeAttribute("cart");
                session.setAttribute("order", order);

                resp.sendRedirect("orderConf.jsp");
            } else {
                req.setAttribute("error", "Failed to create order");
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("POST")) {
            doPost(req, resp);
        } else {
            resp.sendRedirect("checkout.jsp");
        }
    }
}