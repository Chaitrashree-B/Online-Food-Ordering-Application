package com.foodapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Collecting form data from register.html
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        // Setting response type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Displaying collected data
        out.println("<html><head><title>Registration Confirmation</title></head><body>");
        out.println("<h2>Registration Successful!</h2>");
        out.println("<p><strong>Username:</strong> " + username + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Address:</strong> " + address + "</p>");
        out.println("<p>Thank you for registering!</p>");
        out.println("</body></html>");
    }
}
