package com.foodapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.foodapp.daoImpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	 UserDAOImpl udaoi;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String password = req.getParameter("password");
		if(password.equals(req.getParameter("cpassword"))) {
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String address = req.getParameter("address");
		
			udaoi = new UserDAOImpl();
			udaoi.insert(new User(username,password,email,address));
			resp.sendRedirect("login.jsp");
		}
		else {
			resp.getWriter().println("Password Mismatch");
		}
		
	}

}
