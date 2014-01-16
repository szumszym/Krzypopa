package pl.bookingsystem.db.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;
    private static boolean isSessionOpen;
    private static SessionFactory buildSessionFactory() {
        try {

            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getNewSession() {
        isSessionOpen = true;
        session = getSessionFactory().openSession();
        return session;
    }

    private static Session getCurrentSession() {
        session = getSessionFactory().getCurrentSession();
        return session;
    }

    public static Session getSession() {
            return getCurrentSession();
    }

    public static void startTransaction() {
        transaction = getSession().beginTransaction();
    }

    public static Transaction getTransaction() {
        return transaction;
    }

    public static Session start() {
        return start(true);
    }

    public static Session start(boolean startTransaction) {
        Session session = getSession();
        if (startTransaction) startTransaction();
        return session;
    }

    public static void stop() {
        stop(false);
    }

    public static void stop(boolean commit) {
        if (session != null) {
            if (transaction != null) {
                if (commit) {
                    getTransaction().commit();
                } else {
                    getTransaction().rollback();
                }
            }
            getSession().close();
        }
        transaction = null;
        session = null;
        isSessionOpen = false;
    }

    public static void stopAll() {
        stop(false);
        sessionFactory = null;
    }
}

