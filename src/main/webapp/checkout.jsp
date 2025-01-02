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
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .checkout-container {
            background: #212121;
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            max-width: 500px;
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

        .payment-section {
            margin-bottom: 30px;
        }

        .payment-option {
            display: flex;
            align-items: center;
            margin: 15px 0;
            padding: 15px;
            background: #424242;
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

        .order-summary {
            background: #424242;
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