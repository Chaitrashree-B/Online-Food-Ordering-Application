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
        background: url('restaurant_images/cupcake-still-life.jpg') no-repeat center center fixed; /* Placeholder for the background image URL */
        background-size: cover; /* Ensures the image covers the entire viewport */
        color: #FFFFFF; /* White text */
        padding: 20px;
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
        .navbar {
    background: rgba(33, 33, 33, 0); /* Semi-transparent Deep Black */
    padding: 1rem 0;
    position: sticky;
    top: 0;
    z-index: 1000;
    border-bottom: 2px solid rgba(255, 215, 0, 0.2);
    backdrop-filter: blur(5px); /* Optional: Adds a subtle blur effect */
}

.nav-container {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 1rem;
}

.restaurant-card {
    background: rgba(33, 33, 33, 0.5); /* Even more transparent Deep Black background for the card */
    border-radius: 15px; /* Rounds the corners of the card */
    overflow: hidden; /* Ensures content stays within the card boundaries */
    transition: transform 0.3s ease; /* Smooth transition for hover effect */
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3); /* Adds a soft shadow for depth */
    border: 1px solid rgba(255, 215, 0, 0.1); /* Subtle golden border around the card */
}

.restaurant-card:hover {
    transform: translateY(-5px); /* Lifts the card slightly on hover */
    box-shadow: 0 8px 25px rgba(255, 215, 0, 0.2); /* Adds a brighter shadow on hover */
}
   

        .restaurants-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
            padding: 1rem;
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
        .user-menu:hover .user-dropdown,
.user-dropdown:hover {
    display: block;
}
/* Add these styles to your existing CSS */
/* Add these styles to your existing CSS */

/* Smooth transitions for restaurant cards */
.restaurant-card {
    transition: opacity 0.3s ease, transform 0.3s ease;
}

/* No results message styling */
.no-results-message {
    display: none;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 2rem;
    margin: 2rem auto;
    background: #212121;
    border-radius: 15px;
    border: 1px solid rgba(255, 215, 0, 0.1);
    color: #FFFFFF;
    gap: 1rem;
    max-width: 400px;
}

.no-results-message i {
    font-size: 2.5rem;
    color: #FFD700;
}

.no-results-message p {
    font-size: 1.1rem;
    text-align: center;
}

/* Update search input styles */
.nav-search input {
    width: 100%;
    padding: 0.7rem 2.5rem 0.7rem 1rem;
    border-radius: 25px;
    border: 1px solid #FFD700;
    background: #424242;
    color: white;
    font-size: 1rem;
    transition: all 0.3s ease;
}

.nav-search input:focus {
    outline: none;
    box-shadow: 0 0 0 2px rgba(255, 215, 0, 0.3);
}

.nav-search input::placeholder {
    color: rgba(255, 255, 255, 0.6);
}
    </style>


</head>
<body>
 
    <%
    // Get the session
    session = request.getSession(false);
    User user = null;
    List<Restaurant> restaurantList = null;
    
    if (session != null) {
        user = (User) session.getAttribute("User");
        restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
    }
    
    // Only check for restaurant list, not user
    if (restaurantList == null) {
        response.sendRedirect("GetAllRestaurants");
        return;
    }
%>
    <nav class="navbar">
    <div class="nav-container">
        <div class="nav-logo">
            <i class="fas fa-pepper-hot"></i>
            <span>SpiceRoute</span>
        </div>
        
        
      <div class="nav-search">
    <input type="text" placeholder="Search restaurants by name...">
    <i class="fas fa-search"></i>
</div>

        <div class="nav-links">
            <a href="offers.jsp" class="nav-item">
                <i class="fas fa-percent"></i>
                <span>Offers</span>
            </a>
            <a href="#" class="nav-item">
                <i class="fas fa-heart"></i>
                <span>Favorites</span>
            </a>
           <a href="cart.jsp" class="nav-item cart-icon">
    <i class="fas fa-shopping-cart"></i>
    <span>Cart</span>
    <%
        Integer cartItemCount = (Integer) session.getAttribute("cartItemCount");
        if (cartItemCount != null && cartItemCount > 0) {
    %>
        <span class="cart-count"><%= cartItemCount %></span>
    <%
        }
    %>
</a>

<!-- Replace the user menu section with this -->
<div class="nav-item user-menu">
    <i class="fas fa-user"></i>
    <% if (user != null) { %>
        <span><%= user.getUsername() %></span>
        <div class="user-dropdown">
            <a href="profile.jsp">My Profile</a>
            <a href="orders.jsp">Orders</a>
            <a href="addresses.jsp">Addresses</a>
            <a href="signout.jsp">Sign Out</a>
        </div>
    <% } else { %>
        <span>Guest</span>
        <div class="user-dropdown">
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </div>
    <% } %>
</div>

            </div>
        </div>
    </div>
</nav>

<style>
.message {
    padding: 1rem;
    margin: 1rem 0;
    border-radius: 8px;
    text-align: center;
}

.error-message {
    background-color: rgba(183, 28, 28, 0.1);
    border: 1px solid #B71C1C;
    color: #FFD700;
}

.success-message {
    background-color: rgba(255, 215, 0, 0.1);
    border: 1px solid #FFD700;
    color: #FFD700;
}

.nav-logo {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    color: #FFD700;
    font-size: 1.5rem;
    font-weight: bold;
}

.nav-logo i {
    font-size: 1.8rem;
}

.nav-location {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    color: #FFD700;
}

.nav-location select {
    background: #424242;
    border: 1px solid #FFD700;
    color: white;
    padding: 0.5rem;
    border-radius: 4px;
}

.nav-search {
    position: relative;
    flex: 1;
    max-width: 400px;
    margin: 0 2rem;
}

.nav-search input {
    width: 100%;
    padding: 0.7rem 2.5rem 0.7rem 1rem;
    border-radius: 25px;
    border: 1px solid #FFD700;
    background: #424242;
    color: white;
}

.nav-search i {
    position: absolute;
    right: 1rem;
    top: 50%;
    transform: translateY(-50%);
    color: #FFD700;
}

.nav-links {
    display: flex;
    align-items: center;
    gap: 1.5rem;
}

.nav-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: white;
    text-decoration: none;
    gap: 0.3rem;
    position: relative;
}

.nav-item:hover {
    color: #FFD700;
}

.cart-count {
    position: absolute;
    top: -8px;
    right: -8px;
    background: #B71C1C;
    color: white;
    border-radius: 50%;
    padding: 0.2rem 0.5rem;
    font-size: 0.8rem;
    min-width: 20px;
    text-align: center;
}

.user-menu {
    cursor: pointer;
}

.user-dropdown {
    display: none;
    position: absolute;
    top: 100%;
    right: 0;
    background: #212121;
    border: 1px solid #FFD700;
    border-radius: 4px;
    width: 150px;
    margin-top: 0.5rem;
}

.user-dropdown a {
    display: block;
    padding: 0.8rem 1rem;
    color: white;
    text-decoration: none;
}

.user-dropdown a:hover {
    background: #424242;
    color: #FFD700;
}

.user-menu:hover .user-dropdown {
    display: block;
}

@media (max-width: 768px) {
    .nav-search, .nav-location {
        display: none;
    }
    
    .nav-links {
        gap: 1rem;
    }
    
    .nav-item span {
        font-size: 0.8rem;
    }
}
</style>
    <div class="container">
    <div class="header">
        <% if (user != null) { %>
            <h1>Welcome, <%= user.getUsername() %>!</h1>
        <% } else { %>
            <h1>Welcome to SpiceRoute!</h1>
        <% } %>
        <p>Discover premium dining experiences</p>
    </div>

    <h2 class="section-title">Available Restaurants</h2>
    <!-- Rest of the restaurants grid code remains the same -->
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
    <script src="navbar.js"></script>
</body>

</html>
