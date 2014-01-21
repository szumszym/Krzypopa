package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.GenericDAO;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Override
    public T save(T entity) {
        Session session = HibernateUtil.start(true);
        session.saveOrUpdate(entity);
        HibernateUtil.stop(true);
        return entity;
    }

    @Override
    public T merge(T entity) {
        Session session = HibernateUtil.start(true);
        session.merge(entity);
        HibernateUtil.stop(true);
        return entity;
    }

    @Override
    public void deleteByID(String table, ID id) {
        Session session = HibernateUtil.start(true);
        String hql = "delete from " + table + " where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        HibernateUtil.stop(true);
    }

    @Override
    public void delete(T entity) {
        Session session = HibernateUtil.start(true);
        session.delete(entity);
        HibernateUtil.stop(true);
    }

    @Override
    public List selectAll(Class clazz) {
        Session session = HibernateUtil.start(true);
        Query query = session.createQuery("from " + clazz.getName());
        List list = query.list();
        HibernateUtil.stop(false);
        return list;
    }

    @Override
    public List selectMany(String hql) {
        Session session = HibernateUtil.start(true);
        Query query = session.createQuery(hql);
        List t = query.list();
        HibernateUtil.stop(false);
        return t;
    }

    @Override
    public T selectOne(String hql) {
        Session session = HibernateUtil.start(true);
        Query query = session.createQuery(hql);
        T t = (T) query.uniqueResult();
        HibernateUtil.stop(false);
        return t;
    }

    @Override
    public T selectByID(Class clazz, ID id) {
        Session session = HibernateUtil.start(true);
        T t = (T) session.get(clazz, id);
        HibernateUtil.stop(false);
        return t;
    }

    @Override
    public List selectByIDS(Class clazz, List<String> ids) {
        String idsString = generateIdsString(ids);
        Session session = HibernateUtil.start(true);
        Query query = session.createQuery("from " + clazz.getName() + " as x where x.id in " + idsString);
        List list = query.list();
        HibernateUtil.stop(false);
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