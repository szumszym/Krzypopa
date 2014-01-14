package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:08
 */
public interface HotelDAO extends GenericDAO<Hotel, Long> {

    public List<Room> getRooms(Long hotel_id);

    public List selectAllWithAddress();

    void addRoom(Room room, Hotel hotel);
}
