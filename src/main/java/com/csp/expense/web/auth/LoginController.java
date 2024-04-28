/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.csp.expense.web.auth;

import com.csp.expense.db.dao.UserDAO;
import com.csp.expense.db.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author csp38
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = UserDAO.getUser(email);
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("dash");
            } else {
                response.getWriter().print("Email or Password is wrong");
            }
        } catch (SQLException e) {
            response.getWriter().print("Exception occured...");

        }

    }

}
