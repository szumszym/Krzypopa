package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.GenericDAO;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Override
    public T save(T entity) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();

        session.close();

        return entity;
    }

    @Override
    public T merge(T entity) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        session.merge(entity);

        session.getTransaction().commit();

        session.close();
        return entity;
    }

    @Override
    public void delete(T entity) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        session.delete(entity);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List selectAll(Class clazz) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<? extends Object> list = null;
        Query query = session.createQuery("from " + clazz.getName());
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<T> selectMany(Query query) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        List<T> t = (List<T>) query.list();
        session.close();
        return t;
    }

    @Override
    public List<T> selectMany(String hql) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery(hql);

        List<T> t = (List<T>) query.list();
        session.close();
        return t;
    }

    @Override
    public T selectOne(Query query) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        T t = (T) query.uniqueResult();

        session.close();
        return t;
    }

    @Override
    public T selectOne(String hql) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery(hql);

        T t = (T) query.uniqueResult();

        session.close();
        return t;
    }

    @Override
    public T selectByID(Class clazz, ID id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        T t = (T) session.get(clazz, id);
        session.close();
        return t;
    }
}