package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.*;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:08
 */
public interface HotelDAO extends GenericDAO<Hotel, Long> {

    public List getRooms(Long hotel_id);

    public List getStatuses(Long hotel_id);

    public List getAdditions(Long hotel_id);

    public List selectAllWithAddress();

    public List selectAllWithUsers();

    public List selectAllHotelsOfUser(String userId);

    public List selectAllHotels();

    public List selectAllHotelsOfUser(Long userId);

    public Hotel selectByID(String hotelId);

    public Hotel selectByID(Long hotelId);

    void addUser(User user, Hotel hotel);

    void addClient(Client client, Hotel hotel);

    void addRoom(Room room, Hotel hotel);

    void addAddition(Addition addition, Hotel hotel);

    void addStatus(Status status, Hotel hotel);

    public List getHotelFromCity(String city);

    List getRoomFromCity(String city);
}
