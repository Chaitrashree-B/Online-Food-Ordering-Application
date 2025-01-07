<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SpiceRoute Restaurant</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background-color: #424242;
            color: #FFFFFF;
            min-height: 100vh;
            position: relative;
        }

        .background-wrapper {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
        }

        .background-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            position: absolute;
            opacity: 0.7;
        }

        .navbar {
            background-color: rgba(33, 33, 33, 0.95);
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            position: fixed;
            width: 100%;
            z-index: 1000;
        }

        .logo {
            color: #FFD700;
            font-size: 1.5rem;
            font-weight: bold;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .nav-buttons {
            display: flex;
            gap: 1rem;
        }

        .nav-button {
            background-color: #B71C1C;
            color: #FFFFFF;
            padding: 0.5rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .nav-button:hover {
            background-color: #931515;
        }

        .hero {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 4rem 2rem;
            text-align: center;
            min-height: 100vh;
            position: relative;
        }

        .hero h1 {
            color: #FFD700;
            font-size: 3.5rem;
            margin-bottom: 1.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .hero p {
            font-size: 1.2rem;
            max-width: 600px;
            margin-bottom: 2rem;
            line-height: 1.6;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }

        .button-container {
            display: flex;
            gap: 1rem;
            margin-top: 1rem;
        }

        .cta-button {
            background-color: #B71C1C;
            color: #FFFFFF;
            padding: 1rem 2rem;
            border: none;
            border-radius: 4px;
            font-size: 1.1rem;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .cta-button:hover {
            background-color: #931515;
        }

        .browse-button {
            background-color: #1E88E5;
            color: #FFFFFF;
            padding: 1rem 2rem;
            border: none;
            border-radius: 4px;
            font-size: 1.1rem;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .browse-button:hover {
            background-color: #1565C0;
        }

        .features {
            display: flex;
            justify-content: center;
            gap: 2rem;
            margin-top: 3rem;
            flex-wrap: wrap;
        }

        .feature {
            background-color: rgba(33, 33, 33, 0.8);
            padding: 1.5rem;
            border-radius: 8px;
            width: 250px;
            text-align: center;
            backdrop-filter: blur(5px);
        }

        .feature i {
            color: #FFD700;
            font-size: 2rem;
            margin-bottom: 1rem;
        }

        .feature h3 {
            color: #FFD700;
            margin-bottom: 0.5rem;
        }

        .feature p {
            font-size: 0.9rem;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="background-wrapper">
        <picture>
           <source srcset="restaurant_images/welcome.avif" type="image/avif">
            <img src="restaurant_images/welcome.jpg" alt="background" class="background-image">
        </picture>
    </div>
    <nav class="navbar">
        <a href="index.html" class="logo">
            <i class="fas fa-pepper-hot"></i>
            SpiceRoute
        </a>
        <div class="nav-buttons">
            <a href="Register.jsp" class="nav-button">
                <i class="fas fa-user-plus"></i>
                Sign Up
            </a>
            <a href="login.jsp" class="nav-button">
                <i class="fas fa-sign-in-alt"></i>
                Login
            </a>
        </div>
    </nav>
    <main class="hero">
        <h1><i class="fas fa-mortar-pestle"></i> Welcome to SpiceRoute</h1>
        <p>Embark on a culinary journey through the flavors of authentic cuisine. Experience the perfect blend of traditional recipes and modern dining.</p>
        <div class="button-container">
            <a href="Register.jsp" class="cta-button">
                <i class="fas fa-utensils"></i>
                Join Us Today
            </a>
            <a href="GetAllRestaurants" class="browse-button">
                <i class="fas fa-store"></i>
                Browse Restaurants
            </a>
        </div>
    </main>
</body>
</html>
