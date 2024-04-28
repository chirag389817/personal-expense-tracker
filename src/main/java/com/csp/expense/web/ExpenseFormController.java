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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csp38
 */
public class ExpenseFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Category> categorys = CategoryDAO.getCategories(user.getId());

        try {
            String strId = request.getParameter("id");
            int id = Integer.parseInt(strId);
            Expense expense = ExpenseDAO.getExpense(user.getId(), id);
            request.setAttribute("expense", expense);
            request.setAttribute("title", "Update Expenses");
        } catch (Exception e) {
            request.setAttribute("title", "Add Your Expenses");
        }

        request.setAttribute("categories", categorys);

        request.getRequestDispatcher("/WEB-INF/dash/expense-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        String catStr = request.getParameter("category");
        String moneyStr = request.getParameter("money");
        String dateStr = request.getParameter("date");
        String note = request.getParameter("note");

        Expense expense = new Expense();

        try {
            int catId = Integer.parseInt(catStr);
            double money = Double.parseDouble(moneyStr);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

            expense.setUserId(user.getId());
            expense.setCatId(catId);
            expense.setMoney(money);
            expense.setDate(date);
            expense.setNote(note);
        } catch (ParseException ex) {
            System.out.println("Parse error");
            response.sendRedirect("/expense2/dash/expenses");
            return;
        }

        try {
            String strEid = request.getParameter("id");
            int eid = Integer.parseInt(strEid);
            expense.setId(eid);
            ExpenseDAO.delete(user.getId(), eid);
        } catch (Exception e) {
        }
        ExpenseDAO.addExpense(expense);
        response.sendRedirect("/expense2/dash/expenses");
    }

}
