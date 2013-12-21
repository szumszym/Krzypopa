package pl.bookingsystem.db.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import javax.persistence.NonUniqueResultException;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    @Override
    public User.Type checkRegisteredUser(String username, String password) {
        User user = null;
        Session session = null;
        try {
            String sql = "SELECT p FROM User p WHERE p.email = :email AND p.password = :password";
            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery(sql);
            query.setParameter("email", username).setParameter("password", password);

            user = selectOne(query);
            if (user != null) return user.getType();

        } catch (NonUniqueResultException ex) {
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            System.out.println("FIND User.java: " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

}
