package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.GenericDAO;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Override
    public T save(T entity) {
        Session session = HibernateUtil.start();

        session.save(entity);

        HibernateUtil.stop(true);

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
    public void deleteByID(String table, ID id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from " + table + " where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
        session.close();
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
    public List selectMany(String hql) {
        Session session = HibernateUtil.start();
        Query query = session.createQuery(hql);
        List t = query.list();
        HibernateUtil.stop();
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
        Transaction tx = session.beginTransaction();

        T t = (T) session.get(clazz, id);
        tx.commit();
        session.close();
        return t;
    }

    @Override
    public List selectByIDS(Class clazz, List<String> ids) {
        String idsString = generateIdsString(ids);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<? extends Object> list = null;
        Query query = session.createQuery("from " + clazz.getName() + " as x where x.id in " + idsString);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    private String generateIdsString(List<String> ids) {
        String idsString = "(";
        int idsSize = ids.size();
        for (int i = 0; i < idsSize; i++) {
            idsString += ids.get(i);
            if (i < idsSize - 1) {
                idsString += ",";
            }
        }
        idsString += ")";
        return idsString;
    }
}