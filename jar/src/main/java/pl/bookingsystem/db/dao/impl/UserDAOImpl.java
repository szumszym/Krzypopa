package pl.bookingsystem.db.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import javax.persistence.NonUniqueResultException;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    @Override
    public User checkRegisteredUser(String username, String password) {
        User user = null;
        Session session = null;
        try {
            String sql = "SELECT p FROM User p WHERE p.email = :email AND p.password = :password";
            session = HibernateUtil.start();

            Query query = session.createQuery(sql);
            query.setParameter("email", username).setParameter("password", password);

            user = selectOne(query);
            if (user != null) return user;

        } catch (NonUniqueResultException ex) {
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            System.out.println("FIND User.java: " + ex.getMessage());
        } finally {
            if (session != null) {
                HibernateUtil.stop();
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser(String username) {
        User user = null;
        Session session = null;

        try {
            String sql = "SELECT p FROM User p WHERE p.email = :username";
            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery(sql);
            query.setParameter("username", username);

            user = selectOne(query);


        } catch (Exception e) {
            System.out.println(e);
        }
        return user;


    }

    @Override
    public User getOwner(Long hotel_id) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        System.out.println("Befor:");
        Query query = session.createQuery("from hotel_user where hotel_id = :hotel_id ");
        System.out.println("Q1");
        query.setParameter("hotel_id", hotel_id);
        Long user_id = (long) query.executeUpdate();
        System.out.println("hotelID: " + user_id);
        query = session.createQuery("SELECT u FROM User u WHERE u.id = :user_id");
        query.setParameter("user_id", user_id);


        User owner = selectOne(query);
        System.out.println("Q2");
        return owner;
    }


}
