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
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            if (!password.equals(confirmPassword)) {
                response.getWriter().print("both password should be same...");
                return;
            }
            
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            
            UserDAO.addUser(user);
            request.getSession().setAttribute("user", user);
            
            response.sendRedirect("dash");
        } catch (SQLException e) {
            response.getWriter().print("Exception occured...");

        }
    }

}
