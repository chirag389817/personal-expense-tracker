/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csp.expense.db.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author csp38
 */
public class HibernateUtils {
    public static Session getSession() {
        return new Configuration().configure().buildSessionFactory().openSession();
    }
}
