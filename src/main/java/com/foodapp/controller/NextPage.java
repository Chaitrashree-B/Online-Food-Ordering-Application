package com.foodapp.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

/**
 * Servlet implementation class NextPage
 */
@WebServlet("/NextPage")
public class NextPage extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Cookie[] cookiesArr = req.getCookies();
	for(Cookie cookie:cookiesArr) {
		if(cookie.getName().equals("email")) {
			if(cookie.getValue() != null) {
				resp.getWriter().println(cookie.getName() + " " + cookie.getValue());
			}
			
		}
		else {
			resp.sendRedirect("login.html");
		}
	}
	}
	
  
}
