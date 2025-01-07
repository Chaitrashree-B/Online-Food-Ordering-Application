<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.Cart"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #424242;
            background-image: url("restaurant_images/fruits.jpg"); /* Specify your background image here */
            background-size: cover; /* Ensures the image covers the entire background */
            background-repeat: no-repeat; /* Prevents tiling */
            background-position: center center; /* Centers the image */
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .checkout-container {
            background: rgba(33, 33, 33, 0.5); /* Deep Black with 80% opacity for transparency */
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            max-width: 600px;
            width: 90%;
            border: 2px solid #FFD700;
        }

        h1 {
            color: #FFD700;
            text-align: center;
            margin-bottom: 30px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .order-summary {
            background: rgba(66, 66, 66, 0.5); /* Charcoal Gray with 80% opacity for transparency */
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            border: 1px solid #FFD700;
        }

        .order-summary h2 {
            color: #FFD700;
            margin-top: 0;
            font-size: 18px;
            margin-bottom: 15px;
        }

        .order-summary p {
            color: #FFFFFF;
            margin: 10px 0;
            display: flex;
            justify-content: space-between;
        }

        .total {
            border-top: 1px solid #FFD700;
            margin-top: 15px;
            padding-top: 15px;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            color: #FFD700;
            font-size: 16px;
            display: block;
            margin-bottom: 8px;
        }

        .form-group textarea {
            width: 100%;
            height: 80px;
            padding: 10px;
            border-radius: 10px;
            border: 2px solid #FFD700;
            background: rgba(66, 66, 66, 0.5); /* Transparent background for textarea */
            color: #FFFFFF;
            font-size: 14px;
                 max-width: calc(100% - 40px); 
            resize: none;
        }

        .payment-section {
            margin-bottom: 30px;
        }

        .payment-option {
            display: flex;
            align-items: center;
            margin: 15px 0;
            padding: 15px;
            background: rgba(66, 66, 66, 0); /* Transparent background for payment options */
            border-radius: 10px;
            cursor: pointer;
            border: 1px solid transparent;
            transition: all 0.3s ease;
        }

        .payment-option:hover {
            border-color: #FFD700;
        }

        .payment-option input[type="radio"] {
            margin-right: 15px;
            cursor: pointer;
        }

        .payment-option label {
            color: #FFFFFF;
            font-size: 16px;
            cursor: pointer;
            flex: 1;
        }

        .submit-btn {
            background: #B71C1C;
            color: #FFFFFF;
            border: 2px solid #FFD700;
            padding: 15px 30px;
            border-radius: 25px;
            width: 100%;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-top: 20px;
        }

        .submit-btn:hover {
            background: #FFD700;
            color: #212121;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="checkout-container">
        <h1>Checkout</h1>
        
        <% 
            Cart cart = (Cart) session.getAttribute("cart");
            double totalAmount = 0.0;
            if(cart != null) {
                totalAmount = cart.getTotalAmount();
            }
        %>

        <div class="order-summary">
            <h2>Order Summary</h2>
            <p>
                <span>Subtotal:</span>
                <span>₹<%= String.format("%.2f", totalAmount) %></span>
            </p>
            <p>
                <span>Delivery Fee:</span>
                <span>₹40.00</span>
            </p>
            <p class="total">
                <span>Total:</span>
                <span>₹<%= String.format("%.2f", totalAmount + 40.00) %></span>
            </p>
        </div>

        <form action="checkout" method="POST">
            <div class="form-group">
                <label for="deliveryAddress">Delivery Address:</label>
                <textarea id="deliveryAddress" name="deliveryAddress" placeholder="Enter your delivery address here..." required></textarea>
            </div>

            <div class="payment-section">
                <div class="payment-option">
                    <input type="radio" id="cod" name="paymentMode" value="COD" required>
                    <label for="cod">Cash on Delivery</label>
                </div>
                
                <div class="payment-option">
                    <input type="radio" id="upi" name="paymentMode" value="UPI" required>
                    <label for="upi">UPI</label>
                </div>
                
                <div class="payment-option">
                    <input type="radio" id="card" name="paymentMode" value="CARD" required>
                    <label for="card">Credit/Debit Card</label>
                </div>
            </div>

            <button type="submit" class="submit-btn">
                <i class="fas fa-lock"></i> Place Order
            </button>
        </form>
    </div>
</body>
</html>
s