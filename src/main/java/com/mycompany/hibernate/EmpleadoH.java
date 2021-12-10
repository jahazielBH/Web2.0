/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.mycompany.modelo.Empleado;
import com.mycompany.resources.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class EmpleadoH {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public boolean create(Empleado empleado) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(empleado);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return result;
    }

    public boolean update(Empleado empleado) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(empleado);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public Empleado find(long id) {
        Empleado empleado = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("from Empleado e where e.id = :id");
            query.setParameter("id", id);
            empleado = (Empleado) query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            empleado = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empleado;
    }

    public boolean delete(Empleado empleado) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(empleado);
            transaction.commit();
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public List<Empleado> findAll() {
        List<Empleado> empleados = null;
        Session session = null;
        Transaction transaction = null;
        String hql = "FROM Empleado e";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            empleados = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            empleados = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null){
                session.close();
            }
        }
        return empleados;
    }
}
