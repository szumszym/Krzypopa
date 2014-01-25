package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.dao.hibernate.original.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.bookingsystem.db.dao.BaseDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.01.14 @ 10:49
 */
public abstract class BaseDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> implements BaseDAO<T, ID> {
    static final private Configuration configuration = new Configuration().configure();
    static final private SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
    /*new Configuration().configure().buildSessionFactory();//*/

    private Session session;

    public BaseDAOImpl() {
        super();
        super.setSessionFactory(sessionFactory);
    }

    protected Session getSession() {
        if (session == null) {
            return sessionFactory.openSession();
        } else {
            return sessionFactory.getCurrentSession();
        }
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session start(boolean withTransaction) {
        session = getSession();
        if (withTransaction) {
            if (!getSession().getTransaction().isActive()) {
                getSession().beginTransaction();
            }
        }
        return session;
    }

    @Override
    public Session start() {
        return start(true);
    }

    @Override
    public void stop(boolean withCommit) {
        try {
            if (getSession().getTransaction().isActive()) {
                if (withCommit) {
                    getSession().getTransaction().commit();
                } else {
                    getSession().getTransaction().rollback();
                }
            }
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }


    }

    public void stop() {
        stop(false);
    }


    @Override
    public T selectByID(Class<T> clazz, Long id) {
        T t;
        try {
            start();
            t = (T) searchUnique(new Search(clazz).addFilterEqual("id", id));
        } finally {
            stop(false);
        }
        return t;
    }

    @Override
    public List<T> selectByIDs(Class<T> clazz, List<Long> ids) {
        List<T> t;
        try {
            start();
            t = search(new Search(clazz).addFilterIn("id", ids));
        } finally {
            stop(false);
        }
        return t;
    }

    @Override
    public List<T> selectAll(Class<T> clazz) {
        List<T> t;
        try {
            start();
            t = search(new Search(clazz));
        } finally {
            stop(false);
        }
        return t;
    }

    @Override
    public void create(T t) {
        try {
            start();
            super.create(t);
            stop(true);
        } catch (HibernateException e) {
            stop(false);
        }
    }

    @Override
    public void update(T t) {
        try {
            start();
            super.update(t);
            stop(true);
        } catch (HibernateException e) {
            stop(false);
        }
    }

    @Override
    public void deleteByID(ID id) {
        try {
            start();
            super.deleteById(id);
            stop(true);
        } catch (HibernateException e) {
            stop(false);
        }
    }

    @Override
    public void delete(T object) {
        try {
            start();
            super.deleteEntity(object);
            stop(true);
        } catch (HibernateException e) {
            stop(false);
        }
    }

    @Override
    public void deleteOnly(T object) {
        super.deleteEntity(object);
    }


    @Override
    public List<T> selectWith(String property) {
        List<T> t;
        try {
            start();
            t = search(new Search().addFetch(property));
        } finally {
            stop(false);
        }
        return t;
    }

    @Override
    public T selectWith(ID id, String property) {
        T t;
        try {
            start();
            t = (T) searchUnique(new Search().addFilterEqual("id", id).addFetch(property));
        } finally {
            stop(false);
        }
        return t;
    }
}
