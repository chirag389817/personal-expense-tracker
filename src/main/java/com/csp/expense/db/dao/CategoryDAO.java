/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csp.expense.db.dao;

import com.csp.expense.db.model.Category;
import com.csp.expense.db.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class CategoryDAO {
    
    public static boolean addCategory(Category category) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(category);
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

    public static List<Category> getCategories(int userId) {
        try {
            Session session = HibernateUtils.getSession();
            Query<Category> query = session.createQuery("FROM Category WHERE userId = :id", Category.class);
            query.setParameter("id", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Category getCategory(int id){
        try  {
            Session session = HibernateUtils.getSession();
            Query<Category> query = session.createQuery("FROM  Category WHERE id = :id", Category.class);
            query.setParameter("id", id);
            return query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean delete(int catId, int userId){
        try {
            Session session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String sql = "delete from categories where id = :cid";
            Query query = session.createNativeQuery(sql);
            query.setParameter("cid", catId);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

