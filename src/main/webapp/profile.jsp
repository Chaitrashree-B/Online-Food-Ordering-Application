<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.User"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            background-color: #424242; /* Charcoal Gray */
            color: #FFFFFF; /* White text */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 3rem auto;
            padding: 2rem;
            background-color: #212121; /* Deep Black */
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
            border: 1px solid rgba(255, 215, 0, 0.2); /* Subtle golden border */
        }

        .profile-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .profile-header h1 {
            font-size: 2rem;
            color: #FFD700; /* Royal Gold */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .profile-info {
            padding: 1rem 0;
            border-bottom: 1px solid rgba(255, 215, 0, 0.1); /* Golden border line */
        }

        .profile-info:last-child {
            border-bottom: none;
        }

        .profile-info label {
            display: block;
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #FFD700; /* Royal Gold */
        }

        .profile-info p {
            margin: 0;
            font-size: 1rem;
            color: #FFFFFF; /* White */
        }

        .btn-container {
            text-align: center;
            margin-top: 2rem;
        }

        .btn {
            display: inline-block;
            background-color: #B71C1C; /* Rich Maroon */
            color: #FFFFFF; /* White text */
            padding: 0.8rem 2rem;
            border-radius: 25px;
            font-size: 1rem;
            font-weight: bold;
            text-decoration: none;
            transition: all 0.3s ease;
            border: 1px solid #FFD700; /* Royal Gold border */
        }

        .btn:hover {
            background-color: #FFD700; /* Royal Gold */
            color: #212121; /* Deep Black text */
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.3);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="profile-header">
            <h1>My Profile</h1>
        </div>
        <div class="profile-info">
            <label>Username:</label>
            <p><%= user.getUsername() %></p>
        </div>
        <div class="profile-info">
            <label>Email:</label>
            <p><%= user.getEmail() %></p>
        </div>
        <div class="profile-info">
            <label>Address:</label>
            <p><%= user.getAddress() %></p>
        </div>
        <div class="btn-container">
            <a href="editProfile.jsp" class="btn">Edit Profile</a>
        </div>
    </div>
</body>
</html>
