package pl.bookingsystem.db.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import javax.persistence.NonUniqueResultException;
import java.util.List;

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
    public List selectAllOwners() {
        return selectMany("select u from User u where u.type='OWNER'");
    }

    @Override
    public List getEmployeesFromHotel(Long hotelId){
        return selectMany("select u from User u, Hotel h where u.type='EMPLOYEE' and u in elements(h.users) and h.id="+hotelId.toString());
    }


}
