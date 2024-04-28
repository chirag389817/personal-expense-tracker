/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.csp.expense.web;

import com.csp.expense.db.dao.ExpenseDAO;
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
public class DashController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User)request.getSession().getAttribute("user");
        
        List<ExpenseSummary> lst = ExpenseDAO.getPieChartData(user.getId());
        
        String cats="[";
        String moneys="[";
        
        for(ExpenseSummary es : lst){
            cats+="\""+es.getCategory()+"\",";
            moneys+=es.getMoney()+",";
        }
        cats+="]";
        moneys+="]";
        
        request.setAttribute("cats",cats);
        request.setAttribute("moneys",moneys);
        
        request.getRequestDispatcher("/WEB-INF/dash/dash.jsp").forward(request, response);
    }

}
