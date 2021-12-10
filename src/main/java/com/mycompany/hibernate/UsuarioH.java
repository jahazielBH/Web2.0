/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate;

import com.mycompany.modelo.Usuario;
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
public class UsuarioH {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean create(Usuario usuario) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(usuario);
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

    public boolean update(Usuario usuario) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(usuario);
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

    public Usuario find(long uid) {
        Usuario usuario = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("from Usuario u where u.uid = :uid");
            query.setParameter("uid", uid);
            usuario = (Usuario) query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            usuario = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return usuario;
    }

    public boolean delete(Usuario usuario) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(usuario);
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

    public List<Usuario> findAll() {
        List<Usuario> usuarios = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("from Usuario u");
            usuarios = query.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            usuarios = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return usuarios;
    }
    
    public Usuario login(String uname, String password) {
        Session session = null;
        Transaction transaction = null;
        Usuario usr = null;
        String hql;
        try {
            session = sessionFactory.openSession();
            
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("from Usuario u where u.uname = :uname and u.password=:password");
            query.setParameter("uname", uname);
            query.setParameter("password", password);
            usr = (Usuario) query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            usr = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return usr;
    }
}
