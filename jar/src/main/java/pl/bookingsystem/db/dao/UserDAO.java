package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {
    public User checkRegisteredUser(String username, String password);

    public List selectAllOwners();

    List getEmployeesFromHotel(Long hotelId);
}
