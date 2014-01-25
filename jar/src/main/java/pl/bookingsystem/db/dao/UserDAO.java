package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, Long> {
    public User checkRegisteredUser(String username, String password);

    public List<User> selectAllOwners();

    public List<User> getEmployeesFromHotel(Hotel hotel);
}
