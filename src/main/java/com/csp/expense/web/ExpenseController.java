/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.csp.expense.web;

import com.csp.expense.db.dao.CategoryDAO;
import com.csp.expense.db.dao.ExpenseDAO;
import com.csp.expense.db.model.Category;
import com.csp.expense.db.model.Expense;
import com.csp.expense.db.model.ExpenseSummary;
import com.csp.expense.db.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author csp38
 */
public class ExpenseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        List<Expense> expenses;

        try {
            String catString = request.getParameter("catId");
            int catId = Integer.parseInt(catString);
            expenses = ExpenseDAO.getExpenses(user.getId(), catId);
        } catch (Exception e) {
            expenses = ExpenseDAO.getExpenses(user.getId());
        }

        request.setAttribute("expenses", expenses);

        List<Category> categorys = CategoryDAO.getCategories(user.getId());
        Map<Integer, String> catsMap = new HashMap<>();
        for (Category cat : categorys) {
            catsMap.put(cat.getId(), cat.getName());
        }
        request.setAttribute("catsMap", catsMap);

        request.getRequestDispatcher("/WEB-INF/dash/expenses.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            String eidStr = request.getParameter("eid");
            int eid = Integer.parseInt(eidStr);
            ExpenseDAO.delete(user.getId(), eid);
            response.sendRedirect("/expense2/dash/expenses");
        } catch (Exception e) {
            response.getWriter().print("{\"error\":\"Invalid expense id\"}");
        }
    }

}
