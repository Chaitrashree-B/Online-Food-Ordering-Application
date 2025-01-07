<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Luxury Food Service</title>
    <link rel="stylesheet" href="styles.css">
    <style>
    body {
        background-image: url('restaurant_images/fish-with-vegetables-cutlery-black-background.jpg'); /* Replace with your image path */
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0;
    }
    .container {
    background-color: rgba(33, 33, 33, 0.5); /* Semi-transparent background for the form */
    color: #FFFFFF;
    padding: 2rem;
    border-radius: 10px;
    max-width: 400px;
    width: 100%;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin-left: -400px; /* Move it 100px to the left */
}

     .message {
            background-color: #ffe0e0;
            color: #d63031;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            text-align: center;
        }
        .success-message {
            background-color: #e0ffe0;
            color: #27ae60;
        }
</style>

</head>
<body>
    <div class="container">
        <div class="header">
            <div class="logo-container">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="#FFD700">
                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8zm-5-9h10v2H7z"/>
                </svg>
            </div>
            <h1>Welcome Back</h1>
        </div>

        <!-- Display any messages -->
        <% if (request.getAttribute("message") != null) { %>
            <div class="message success-message">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>

        <!-- Display any error messages -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="message">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="submit-btn">Login</button>
        </form>
        <p class="form-footer">
            If you are a new user, <a href="Register.jsp">Register Here</a>.
        </p>
    </div>
</body>
</html>
