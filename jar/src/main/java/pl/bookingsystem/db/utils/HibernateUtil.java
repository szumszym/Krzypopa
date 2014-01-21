package pl.bookingsystem.db.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StringType;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addSqlFunction("group_concat", new StandardSQLFunction("group_concat", new StringType()));
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }

    public static Session start(boolean startTransaction){
        Session session = sessionFactory.getCurrentSession();
        if(startTransaction){
            session.beginTransaction();
        }
        return session;
    }

    public static void stop(boolean commit){
        if(commit){
            sessionFactory.getCurrentSession().getTransaction().commit();
        }   else {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    public static void stopAll() {
        stop(false);
        sessionFactory.getCurrentSession().close();
        sessionFactory = null;
    }
}

