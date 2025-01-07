<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.Menu, com.foodapp.model.Restaurant,com.foodapp.dao.MenuDAO,
com.foodapp.dao.RestaurantDAO, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

          body {
            background: url("restaurant_images/dining_plate.jpg") no-repeat center center fixed; /* Add your background image here */
            background-size: cover; /* Ensures the image covers the entire background */
            padding: 20px;
            color: #FFFFFF;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        .restaurant-header {
            background: linear-gradient(135deg, #FFD700, #B71C1C);
            color: #FFFFFF;
            padding: 2rem;
            border-radius: 15px;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.2);
        }

        .restaurant-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .restaurant-details h1 {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .back-button {
            background: #212121;
            color: #FFD700;
            padding: 0.5rem 1rem;
            border-radius: 25px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            border: 1px solid #FFD700;
            transition: all 0.3s ease;
        }

        .back-button:hover {
            background: #FFD700;
            color: #212121;
        }

        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
            padding: 1rem;
        }

       .menu-card {
    background: rgba(33, 33, 33, 0); /* Set transparency using rgba */
    border-radius: 15px;
    overflow: hidden;
    transition: transform 0.3s ease, background 0.3s ease;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
    border: 1px solid rgba(255, 215, 0, 0.1);
}

.menu-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(255, 215, 0, 0.2);
    background: rgba(33, 33, 33, 0.9); /* Slightly reduce transparency on hover */
}


        .menu-image {
            height: 200px;
            position: relative;
            overflow: hidden;
        }

        .menu-image img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* Ensures the image fills the card proportionally */
        }

        .menu-content {
            padding: 1.5rem;
        }

        .menu-name {
            font-size: 1.25rem;
            font-weight: 600;
            color: #FFD700;
            margin-bottom: 0.5rem;
        }

        .menu-description {
            color: #FFFFFF;
            margin-bottom: 1rem;
            font-size: 0.9rem;
            line-height: 1.4;
        }

        .menu-price {
            color: #FFD700;
            font-size: 1.2rem;
            font-weight: 600;
        }

        .add-to-cart {
            display: inline-flex;
            align-items: center;
            padding: 0.5rem 1.2rem;
            background: #B71C1C;
            color: #FFFFFF;
            border-radius: 25px;
            font-size: 0.9rem;
            font-weight: 500;
            cursor: pointer;
            border: 1px solid #FFD700;
            gap: 0.5rem;
            margin-top: 1rem;
            transition: all 0.3s ease;
        }

        .add-to-cart:hover {
            background: #FFD700;
            color: #212121;
        }

        .availability-badge {
            position: absolute;
            top: 1rem;
            right: 1rem;
            background: rgba(33, 33, 33, 0.9);
            color: #FFD700;
            padding: 0.3rem 0.8rem;
            border-radius: 15px;
            font-size: 0.8rem;
        }

        @media (max-width: 768px) {
            .menu-grid {
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            }
        }
    </style>
</head>
<body>
<%
// Retrieve restaurant and menu items from the request
Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");

// Add error message handling
String error = (String) request.getAttribute("error");
if (error != null) {
    %>
    <div class="error-message">
        <%= error %>
        <a href="home.jsp">Return to Home</a>
    </div>
    <%
    return;
}

if (restaurant == null || menuItems == null) {
    response.sendRedirect("menu?restaurantId=" + request.getParameter("restaurantId"));
    return;
}
%>

    <div class="container">
        <div class="restaurant-header">
            <div class="restaurant-info">
                <div class="restaurant-details">
                    <h1><%= restaurant.getRestaurantName() %></h1>
                    <p><i class="fas fa-map-marker-alt"></i> <%= restaurant.getAddress() %></p>
                    <p><i class="fas fa-star"></i> <%= restaurant.getRatings() %> / 5</p>
                </div>
                <a href="home.jsp" class="back-button">
                    <i class="fas fa-arrow-left"></i>
                    Back to Restaurants
                </a>
            </div>
        </div>

        <div class="menu-grid">
            <% for (Menu item : menuItems) { %>
            <div class="menu-card">
                <div class="menu-image">
                    <img src="<%= request.getContextPath() + '/' + item.getImagePath() %>" 
                         alt="<%= item.getMenuName() %>">
                    <span class="availability-badge">
                        <%= item.isAvailable() ? "Available" : "Not Available" %>
                    </span>
                </div>

                <div class="menu-content">
                    <h3 class="menu-name"><%= item.getMenuName() %></h3>
                    <p class="menu-description"><%= item.getDescription() %></p>
                    <div class="menu-price">₹<%= item.getPrice() %></div>
                    <% if (item.isAvailable()) { %>
                    <form action="CartServlet" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="itemId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
                        <input type="hidden" name="name" value="<%= item.getMenuName() %>">
                        <input type="hidden" name="price" value="<%= item.getPrice() %>">
                        <button type="submit" class="add-to-cart">
                            <i class="fas fa-shopping-cart"></i>
                            Add to Cart
                        </button>
                    </form>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>
    </div>
    <script>
        document.querySelectorAll('form').forEach(form => {
            form.addEventListener('submit', function() {
                const button = this.querySelector('.add-to-cart');
                button.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Adding...';
                button.style.opacity = '0.7';
                button.disabled = true;
            });
        });
    </script>
</body>
</html>

