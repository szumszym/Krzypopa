package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

import java.util.List;

public class UserDAOImpl extends BaseDAOImpl<User, Long> implements UserDAO {

    @Override
    public User checkRegisteredUser(String email, String password) {
        User t;
        try {
            start();
            t = (User) searchUnique(new Search(User.class)
                    .addFilterEqual("email", email)
                    .addFilterEqual("password", password));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public boolean checkIfEmailIsInDB(String email) {
        User user;
        try {
            start();
            user = (User) searchUnique(new Search(User.class)/*.setResultMode(ISearch.RESULT_SINGLE)*/
                    .addFilterEqual("email", email));
        } finally {
            stop(false);
        }
        return user != null;
    }

    @Override
    public List<User> selectAllOwners() {
        List<User> t;
        try {
            start();
            t = search(new Search(User.class)
                    .addFilterEqual("type", User.Type.OWNER));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public List<User> getEmployeesFromHotel(Hotel hotel) {
        List<User> t;
        try {
            start();
            t = search(new Search(User.class)
                    .addFilterEqual("type", User.Type.EMPLOYEE)
                    .addFilterSome("hotels", Filter.in(Filter.ROOT_ENTITY, hotel)));
        } finally {
            stop();
        }
        return t;
    }

}
