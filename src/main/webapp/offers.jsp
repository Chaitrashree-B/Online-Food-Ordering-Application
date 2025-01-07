<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exclusive Offers</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #424242; /* Charcoal Gray */
            color: #FFFFFF; /* White */
            background-image: url("restaurant_images/top-view-french-fries-with-mustard-copy-space.jpg"); /* Add your background image here */
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            min-height: 100vh;
        }

        header {
            background-color: rgba(33, 33, 33, 0.7); /* Deep Black with slight transparency */
            padding: 20px;
            text-align: center;
        }

        header h1 {
            color: #FFD700; /* Royal Gold */
        }

        .offers-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }

        .offer-card {
            background-color: rgba(33, 33, 33, 0.7); /* Deep Black with slight transparency */
            border: 2px solid #FFD700; /* Royal Gold */
            border-radius: 8px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .offer-card h2 {
            color: #FFD700; /* Royal Gold */
        }

        .offer-card p {
            margin: 10px 0;
        }

        .offer-card button {
            background-color: #B71C1C; /* Rich Maroon */
            color: #FFFFFF; /* White */
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .offer-card button:hover {
            background-color: #FFD700; /* Royal Gold */
            color: #212121; /* Deep Black */
        }
    </style>
</head>
<body>
    <header>
        <h1>Exclusive Food Offers</h1>
    </header>

    <div class="offers-container">
        <div class="offer-card">
            <h2>Buy 1 Get 1 Free Pizza</h2>
            <p>Delight in every slice. Add this amazing offer to your cart now!</p>
            <form action="CartServlet" method="post">
                <input type="hidden" name="item" value="Buy 1 Get 1 Free Pizza">
                <button type="submit">Add to Cart</button>
            </form>
        </div>

        <div class="offer-card">
            <h2>25% Off Burgers</h2>
            <p>Juicy and delicious burgers at a special price. Don't miss out!</p>
            <form action="CartServlet" method="post">
                <input type="hidden" name="item" value="25% Off Burgers">
                <button type="submit">Add to Cart</button>
            </form>
        </div>

        <div class="offer-card">
            <h2>Free Dessert with Orders Above $20</h2>
            <p>Sweeten your meal with a complimentary dessert.</p>
            <form action="CartServlet" method="post">
                <input type="hidden" name="item" value="Free Dessert with Orders Above $20">
                <button type="submit">Add to Cart</button>
            </form>
        </div>
    </div>
</body>
</html>
