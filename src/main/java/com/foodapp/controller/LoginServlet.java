package com.foodapp.controller;

import java.io.IOException;
import java.sql.Connection;
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
                    session.setAttribute("User", user);

                    // Check for redirect URL in session
                    String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
                    if (redirectUrl != null) {
                        session.removeAttribute("redirectAfterLogin");
                        resp.sendRedirect(redirectUrl);
                    } else {
                        resp.sendRedirect("GetAllRestaurants");
                    }
                } else {
                    req.setAttribute("error", "Invalid password. Please try again.");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "User not found. Please register.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during login. Please try again.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
