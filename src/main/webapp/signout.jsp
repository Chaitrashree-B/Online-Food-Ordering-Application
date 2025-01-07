<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    // Invalidate the current session to log the user out
    session = request.getSession(false);
    if (session != null) {
        session.invalidate(); // Invalidates the session
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Out</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body style="background-color: #424242; color: #ffffff; font-family: Arial, sans-serif;">
    <div class="container" style="text-align: center; padding: 50px;">
        <h1 style="color: #FFD700;">You have successfully logged out!</h1>
        <p style="color: #B71C1C;">Thank you for using our service.</p>
        <a href="login.jsp" style="text-decoration: none; color: #FFD700; font-size: 20px;">Go to Login Page</a>
    </div>
</body>
</html>
