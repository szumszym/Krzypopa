package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {
    public User.Type checkRegisteredUser(String username, String password);

    public User getCurrentUser(String username);


}
