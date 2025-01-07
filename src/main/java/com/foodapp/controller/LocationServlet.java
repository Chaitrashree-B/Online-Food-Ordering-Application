package com.foodapp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/updateLocation")
public class LocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String location = request.getParameter("location");
        HttpSession session = request.getSession();
        session.setAttribute("userLocation", location);
        // Update restaurant list based on location
        response.sendRedirect("home.jsp");
    }
}
