<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.Order"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #424242;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .confirmation-container {
            background: #212121;
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            text-align: center;
            max-width: 500px;
            width: 90%;
            animation: slideIn 0.6s ease-out;
            border: 2px solid #FFD700;
        }

        @keyframes slideIn {
            0% {
                transform: translateY(-50px);
                opacity: 0;
            }
            100% {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .checkmark {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #B71C1C;
            margin: 0 auto 20px;
            animation: scaleIn 0.5s ease-out 0.6s both;
            border: 2px solid #FFD700;
        }

        @keyframes scaleIn {
            0% {
                transform: scale(0);
            }
            50% {
                transform: scale(1.2);
            }
            100% {
                transform: scale(1);
            }
        }

        .checkmark i {
            color: #FFD700;
            font-size: 40px;
            animation: check 0.5s ease-out 1s both;
        }

        @keyframes check {
            0% {
                transform: scale(0);
            }
            100% {
                transform: scale(1);
            }
        }

        h1 {
            color: #FFD700;
            margin-bottom: 1.5rem;
            font-size: 24px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .order-details {
            margin: 20px 0;
            padding: 20px;
            background: #424242;
            border-radius: 10px;
            animation: fadeIn 0.6s ease-out 0.8s both;
            border: 1px solid #FFD700;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        .order-details p {
            margin: 12px 0;
            color: #FFFFFF;
            font-size: 16px;
        }

        .order-details strong {
            color: #FFD700;
        }

        .back-button {
            display: inline-block;
            padding: 14px 28px;
            background: #B71C1C;
            color: #FFFFFF;
            text-decoration: none;
            border-radius: 25px;
            transition: all 0.3s ease;
            margin-top: 20px;
            font-weight: bold;
            border: 2px solid #FFD700;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .back-button:hover {
            background: #FFD700;
            color: #212121;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(255, 215, 0, 0.3);
        }

        .delivery-animation {
            margin: 30px 0;
            animation: bounce 2s infinite;
        }

        @keyframes bounce {
            0%, 100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-10px);
            }
        }

        .delivery-animation i {
            color: #FFD700;
            font-size: 48px;
            text-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
        }

        .status-message {
            color: #FFFFFF;
            margin: 15px 0;
            font-size: 16px;
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% {
                opacity: 0.6;
            }
            50% {
                opacity: 1;
            }
            100% {
                opacity: 0.6;
            }
        }
    </style>
</head>
<body>
    <div class="confirmation-container">
        <div class="checkmark">
            <i class="fas fa-check"></i>
        </div>
        
        <h1>Order Confirmed!</h1>
        
        <div class="delivery-animation">
            <i class="fas fa-motorcycle"></i>
        </div>

        <div class="order-details">
            <% Order order = (Order)session.getAttribute("order"); %>
            <p><strong>Total Amount:</strong> ₹<%= String.format("%.2f", order.getTotalAmount()) %></p>
            <p><strong>Payment Method:</strong> <%= order.getPaymentMode() %></p>
            <p><strong>Status:</strong> <%= order.getStatus() %></p>
        </div>

        <p class="status-message">Your delicious food is being prepared with care!</p>
        
        <a href="GetAllRestaurants" class="back-button">
            <i class="fas fa-arrow-left"></i> Back to Restaurants
        </a>
    </div>
</body>
</html>