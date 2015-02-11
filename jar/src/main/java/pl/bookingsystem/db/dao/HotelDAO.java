package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:08
 */
public interface HotelDAO extends BaseDAO<Hotel, Long> {

    public List<Hotel> selectAllWithAddress();

    public List<Hotel> selectAllWithUsers();

    public List<Hotel> selectAllHotels();

    public List<Hotel> selectAllHotelsOfUser(Long userId);

    public Hotel selectByID(Long hotelId);

    void addRoom(Room room, Hotel hotel);

    void addAddition(Addition addition, Hotel hotel);

    void addStatus(Status status, Hotel hotel);

    public List<Hotel> getHotelFromCity(String city);
}
