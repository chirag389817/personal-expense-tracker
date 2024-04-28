/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csp.expense.db.dao;

import com.csp.expense.db.model.Expense;
import com.csp.expense.db.model.ExpenseSummary;
import com.csp.expense.db.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class ExpenseDAO {
    
    public static boolean addExpense(Expense expense) {
        Transaction transaction = null;
        try  {
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(expense);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Expense> getExpenses(int userId, int catId) {
        try  {
            Session session = HibernateUtils.getSession();
            Query<Expense> query = session.createQuery("FROM  Expense WHERE userId = :uid AND catId = :cid ORDER BY date DESC", Expense.class);
            query.setParameter("uid", userId);
            query.setParameter("cid", catId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Expense getExpense(int userId, int expId) {
        try  {
            Session session = HibernateUtils.getSession();
            Query<Expense> query = session.createQuery("FROM  Expense WHERE userId = :uid AND id = :eid", Expense.class);
            query.setParameter("uid", userId);
            query.setParameter("eid", expId);
            return query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Expense> getExpenses(int userId) {
        try {
            Session session = HibernateUtils.getSession();
            Query<Expense> query = session.createQuery("FROM  Expense WHERE userId = :uid ORDER BY date DESC", Expense.class);
            query.setParameter("uid", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean delete(int userId, int expenseId){
        try {
            Session session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String sql = "delete from expenses where id = :eid AND userId = :uid";
            Query query = session.createNativeQuery(sql);
            query.setParameter("eid", expenseId);
            query.setParameter("uid", userId);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<ExpenseSummary> getPieChartData(int userId){
        try{
            Session session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            
            String sql = "select name as category, sum(money) as money from expenses inner join categories on expenses.catId=categories.id where categories.userId=:uid group by catId";
            NativeQuery<Object[]> query = session.createSQLQuery(sql);
            query.setParameter("uid", userId);
            
            List<Object[]> rows = query.getResultList();
            List<ExpenseSummary> expenseSummaries = new ArrayList<>();
            for (Object[] row : rows) {
                String categoryName = (String) row[0];
                double totalMoney = (double) row[1];
                expenseSummaries.add(new ExpenseSummary(categoryName, totalMoney));
            }
            transaction.commit();
            return expenseSummaries;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ExpenseSummary>();
        }
    }
}

