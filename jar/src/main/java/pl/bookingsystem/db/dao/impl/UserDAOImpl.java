package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

import java.util.List;

public class UserDAOImpl extends BaseDAOImpl<User, Long> implements UserDAO {

    @Override
    public User checkRegisteredUser(String email, String password) {
        User t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = (User) searchUnique(new Search(User.class)
                    .addFilterEqual("email", email)
                    .addFilterEqual("password", password));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public boolean checkIfEmailIsInDB(String email) {
        User user;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = (User) searchUnique(new Search(User.class)/*.setResultMode(ISearch.RESULT_SINGLE)*/
                    .addFilterEqual("email", email));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return user != null;
    }

    @Override
    public List<User> selectAllOwners() {
        List<User> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(User.class)
                    .addFilterEqual("type", User.Type.OWNER));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<User> getEmployeesFromHotel(Hotel hotel) {
        List<User> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(User.class)
                    .addFilterEqual("type", User.Type.EMPLOYEE)
                    .addFilterSome("hotels", Filter.in(Filter.ROOT_ENTITY, hotel)));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

}
