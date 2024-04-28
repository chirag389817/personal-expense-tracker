/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csp.expense.db.dao;

import com.csp.expense.db.model.User;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {

    public static void addUser(User user) throws SQLException {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public static User getUser(String email) throws SQLException {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery("FROM  User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultList().get(0);
    }

}
