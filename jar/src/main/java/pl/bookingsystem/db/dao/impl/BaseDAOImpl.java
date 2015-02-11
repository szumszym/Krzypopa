package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.dao.hibernate.original.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pl.bookingsystem.db.dao.BaseDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.01.14 @ 10:49
 * Update Date: 10.06.14 @ 19:59
 */
public abstract class BaseDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> implements BaseDAO<T, ID> {
    private SessionFactory SESSION_FACTORY;
    private Session session;

    /**
     * Default database is MySql database
     */
    public BaseDAOImpl() {
        this("/hibernate-mysql.cfg.xml");
    }

    /**
     * @param cfgFile path to hibernate cfg.xml file to different database
     */
    public BaseDAOImpl(String cfgFile) {
        super();

        Configuration configuration = new AnnotationConfiguration();
        configuration.configure(cfgFile);
        SESSION_FACTORY = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

        super.setSessionFactory(SESSION_FACTORY);
    }


    public Session getSession() {
        if (session == null) {
            return SESSION_FACTORY.getCurrentSession();
        } else {
            return session;//SESSION_FACTORY.getCurrentSession();
        }
    }

    protected SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
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
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            t = (T) searchUnique(new Search(clazz).addFilterEqual("id", id));
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
        return t;
    }

    @Override
    public List<T> selectByIDs(Class<T> clazz, List<Long> ids) {
        List<T> t;
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            t = search(new Search(clazz).addFilterIn("id", ids));
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
        return t;
    }

    @Override
    public List<T> selectAll(Class<T> clazz) {
        List<T> t;
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            t = search(new Search(clazz));
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
        return t;
    }

    @Override
    public void create(T t) {
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            super.create(t);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
    }

    @Override
    public void update(T t) {
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            super.update(t);
            transaction.commit();
        } catch (StaleObjectStateException e) {
            transaction.rollback();
            throw new StaleObjectStateException(e.getEntityName(), e.getIdentifier());
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            if(session1!= null && session1.isOpen()){
                session1.close();}
        }
    }

    @Override
    public void deleteByID(ID id) {
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            super.deleteById(id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
    }

    @Override
    public void delete(T object) {
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session1.beginTransaction();
        try {
            super.deleteEntity(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
    }

    @Override
    public void deleteOnly(T object) {
        super.deleteEntity(object);
    }


    @Override
    public List<T> selectWith(String property) {
        List<T> t;
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search().addFetch(property));
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
        return t;
    }

    @Override
    public T selectWith(ID id, String property) {
        T t;
        Session session1 = SESSION_FACTORY.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = (T) searchUnique(new Search().addFilterEqual("id", id).addFetch(property));
        } finally {
            if(session1!= null && session1.isOpen())session1.close();
        }
        return t;
    }
}
