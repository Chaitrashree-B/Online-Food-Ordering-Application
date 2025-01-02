package com.foodapp.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.DBUtil.DBConnection;
import com.foodapp.model.User;
import com.foodapp.dao.UserDAO;
import com.foodapp.daoImpl.UserDAOImpl;

import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static Connection con;
    private static final String FETCH_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private PreparedStatement pstmt;
    private ResultSet res;

    static {
        con = DBConnection.connect();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            pstmt = con.prepareStatement(FETCH_BY_EMAIL);
            pstmt.setString(1, email);
            res = pstmt.executeQuery();

            if (res.next()) {
                String dbPassword = res.getString("password");
                if (password.equals(dbPassword)) {
                    UserDAO udao = new UserDAOImpl();
                    User user = udao.fetchOne(email);

                    // Set user details in session
                    session.setAttribute("User", user);

                    // Redirect to GetAllRestaurants
                    resp.sendRedirect("GetAllRestaurants");
                } else {
                    resp.sendRedirect("failure.jsp");
                }
            } else {
                resp.sendRedirect("failure.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
