<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.foodapp.model.CartItems, com.foodapp.model.Cart" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        /* Luxurious & Premium Theme */
        body {
            font-family: Arial, sans-serif;
            background-color: #424242; /* Charcoal Gray */
            color: #FFFFFF; /* Text: White */
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
            background-color: #424242; /* Charcoal Gray */
        }

        .cart-header {
            text-align: center;
            padding: 20px;
            background-color: #212121; /* Deep Black */
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .cart-header h2 {
            color: #FFD700; /* Royal Gold */
        }

        .cart-item {
            background-color: #333333;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .cart-item h3, .cart-item p {
            color: #FFFFFF; /* White text */
        }

        .quantity-btn, .remove-btn {
            background-color: #B71C1C; /* Rich Maroon */
            color: white;
            border: none;
            padding: 8px 15px;
            margin: 5px;
            cursor: pointer;
            border-radius: 5px;
        }

        .quantity-btn:hover, .remove-btn:hover {
            background-color: #FFD700; /* Royal Gold on hover */
        }

        .cart-summary h3 {
            color: #FFD700; /* Royal Gold */
            font-size: 20px;
        }

        .action-button {
            display: inline-block;
            background-color: #FFD700; /* Royal Gold */
            color: #212121; /* Deep Black */
            padding: 12px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            margin-top: 15px;
        }

        .action-button:hover {
            background-color: #B71C1C; /* Rich Maroon */
            color: white;
        }

        .cart-actions {
            margin-top: 30px;
            text-align: center;
        }

        /* Responsive design for smaller screens */
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
            .cart-item {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="cart-header">
            <h2>Your Cart</h2>
        </div>

        <% 
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
        %>
            <p>Your cart is empty. <a href="home.jsp" class="action-button">Go To Home</a></p>
        <% 
        } else {
        	int restaurantId = 0;
        	if(!cart.getItems().isEmpty()){
        		restaurantId = cart.getItems().values().iterator().next().getRestaurantId();
        	}
            Map<Integer, CartItems> items = cart.getItems();
            for (Map.Entry<Integer, CartItems> entry : items.entrySet()) {
                CartItems item = entry.getValue();
        %>
            <div class="cart-item">
                <h3><%= item.getName() %></h3>
                <p>Price: ₹<%= item.getPrice() %></p>
                <p>Quantity: <%= item.getQuantity() %></p>
                <form action="CartServlet" method="POST" style="display: inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit" class="quantity-btn" name="quantity" value="<%= item.getQuantity() + 1 %>">+</button>
                    <button type="submit" class="quantity-btn" name="quantity" value="<%= item.getQuantity() - 1 %>">-</button>
                </form>
                <form action="CartServlet" method="POST" style="display: inline;">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </div>
        <% } %>

        <div class="cart-summary">
            <h3>Total Amount: ₹<%= cart.getTotalAmount() %></h3>
        </div>

        <div class="cart-actions">
            <!-- "Add More Items" button that redirects to the restaurant menu -->
    <a href="menu?restaurantId=<%= restaurantId %>" class="action-button">Add More Items</a>
            <a href="checkout.jsp" class="action-button">Proceed to Checkout</a>
        </div>
        <% } %>
    </div>
</body>
</html>
