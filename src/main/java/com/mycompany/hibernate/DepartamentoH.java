/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate;

import com.mycompany.modelo.Departamento;
import com.mycompany.resources.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class DepartamentoH {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean create(Departamento departamento) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(departamento);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return result;
    }

    public boolean update(Departamento departamento) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(departamento);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return result;
    }

    public Departamento find(long id) {
        Departamento departamento = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("from Departamento d where d.id = :id");
            query.setParameter("id", id);
            departamento = (Departamento) query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            departamento = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return departamento;
    }

    public boolean delete(Departamento departamento) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(departamento);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return result;
    }

    public List<Departamento> findAll() {
        List<Departamento> departamento = null;
        Session session = null;
        Transaction transaction = null;
        String hql = "FROM Departamento d";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            departamento = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            departamento = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return departamento;
    }
}
