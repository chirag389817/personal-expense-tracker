/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.csp.expense.web;

import com.csp.expense.db.dao.CategoryDAO;
import com.csp.expense.db.dao.ExpenseDAO;
import com.csp.expense.db.model.Category;
import com.csp.expense.db.model.ExpenseSummary;
import com.csp.expense.db.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author csp38
 */
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        List<Category> categorys = CategoryDAO.getCategories(user.getId());
        request.setAttribute("categories", categorys);
        request.getRequestDispatcher("/WEB-INF/dash/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String cateString = request.getParameter("category");
        User user = (User)request.getSession().getAttribute("user");
        
        Category category = new Category();
        category.setName(cateString);
        category.setUserId(user.getId());
        CategoryDAO.addCategory(category);
        
        response.sendRedirect("/expense2/dash/categories");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);
        User user = (User)request.getSession().getAttribute("user");
        CategoryDAO.delete(id, user.getId());
        
        System.out.println("deleted "+id);
        request.getRequestDispatcher("/WEB-INF/dash/categories.jsp").forward(request, response);
    }
    
    

}
