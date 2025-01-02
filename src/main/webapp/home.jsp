<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession, com.foodapp.model.User, com.foodapp.model.Restaurant" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Home</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #424242;  /* Charcoal Gray background */
            padding: 20px;
            color: #FFFFFF;  /* White text */
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        .header {
            background: linear-gradient(135deg, #FFD700, #B71C1C);  /* Royal Gold to Rich Maroon gradient */
            color: #FFFFFF;
            padding: 2rem;
            border-radius: 15px;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.2);  /* Golden shadow */
        }

        .header h1 {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .restaurants-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
            padding: 1rem;
        }

        .restaurant-card {
            background: #212121;  /* Deep Black */
            border-radius: 15px;
            overflow: hidden;
            transition: transform 0.3s ease;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
            border: 1px solid rgba(255, 215, 0, 0.1);  /* Subtle golden border */
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(255, 215, 0, 0.2);
        }

        .card-image {
            height: 200px;
            background: #2a2a2a;
            display: flex;
            align-items: center;
            justify-content: center;
            border-bottom: 2px solid rgba(255, 215, 0, 0.2);  /* Subtle golden line */
        }

        .card-content {
            padding: 1.5rem;
            position: relative;
        }

        .restaurant-name {
            font-size: 1.25rem;
            font-weight: 600;
            color: #FFD700;  /* Royal Gold */
            margin-bottom: 0.5rem;
        }

        .restaurant-location {
            color: #FFFFFF;
            margin-bottom: 1rem;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .restaurant-location i {
            color: #FFD700;  /* Royal Gold */
        }

        .restaurant-rating {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            color: #FFD700;  /* Royal Gold */
            font-weight: 600;
            margin-bottom: 1rem;
        }

        .section-title {
            color: #FFD700;  /* Royal Gold */
            margin: 2rem 0 1rem;
            font-size: 1.75rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .view-menu-chip {
            display: inline-flex;
            align-items: center;
            padding: 0.5rem 1.2rem;
            background: #B71C1C;  /* Rich Maroon */
            color: #FFFFFF;
            border-radius: 25px;
            font-size: 0.9rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            border: 1px solid #FFD700;  /* Royal Gold border */
            gap: 0.5rem;
        }

        .view-menu-chip:hover {
            transform: translateY(-2px);
            background: #FFD700;  /* Royal Gold on hover */
            color: #212121;  /* Deep Black text on hover */
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.3);
        }

        .view-menu-chip i {
            transition: all 0.3s ease;
        }

        .view-menu-chip:hover i {
            color: #212121;  /* Deep Black icon on hover */
        }

        @media (max-width: 768px) {
            .restaurants-grid {
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            }
        }
    </style>
</head>
<body>
    <%
        // Get the session
        session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Retrieve the User object
        User user = (User) session.getAttribute("User");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Retrieve the Restaurant List
        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
        if (restaurantList == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="container">
        <div class="header">
            <h1>Welcome, <%= user.getUsername() %>!</h1>
            <p>Discover premium dining experiences</p>
        </div>

        <h2 class="section-title">Available Restaurants</h2>
        <div class="restaurants-grid">
            <% for (Restaurant restaurant : restaurantList) { %>
            <div class="restaurant-card">
                <<div class="card-image">
    <% if (restaurant.getImagePath() != null && !restaurant.getImagePath().isEmpty()) { %>
        <img src="restaurant_images/<%= restaurant.getImagePath().substring(
            restaurant.getImagePath().lastIndexOf('/') + 1) %>" 
            alt="<%= restaurant.getRestaurantName() %>"
            style="width: 100%; height: 100%; object-fit: cover;">
    <% } else { %>
        <img src="/api/placeholder/300/200" alt="Restaurant Image">
    <% } %>
</div>

                <div class="card-content">
                    <h3 class="restaurant-name"><%= restaurant.getRestaurantName() %></h3>
                    <div class="restaurant-location">
                        <i class="fas fa-map-marker-alt"></i>
                        <%= restaurant.getAddress() %>
                    </div>
                    <div class="restaurant-rating">
                        <i class="fas fa-star"></i>
                        <%= restaurant.getRatings() %> / 5
                    </div>
                    <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="view-menu-chip">
    <i class="fas fa-utensils"></i>
    View Menu
</a>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>