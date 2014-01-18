package pl.bookingsystem.db.dao.impl;

import org.hibernate.Session;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {
    @Override
    public List<Room> getRooms(Long hotel_id) {
        return selectMany("select h.rooms from Hotel h where h.id ="+String.valueOf(hotel_id));
    }

    @Override
    public List selectAllWithAddress() {
        return selectMany("from Hotel as h left join fetch h.address");
    }

    @Override
    public List selectAllWithUsers() {
        return selectMany("from Hotel as h left join fetch h.users");
    }

    @Override
    public List selectAllHotelsOfUser(String userId) {
        return selectMany("select hotels from User as u left join u.hotels hotels left join fetch hotels.address address where u.id=" + userId);
    }

    @Override
    public List selectAllHotelsOfUser(Long userId) {
        return selectAllHotelsOfUser(String.valueOf(userId));
    }

    @Override
    public List selectAllHotels() {
        return selectMany("from Hotel as h fetch all properties");
    }


    @Override
    public Hotel selectByID(String hotelId){
        return selectByID(Hotel.class, Long.valueOf(hotelId));
    }

    @Override
    public void addUser(User user, Hotel hotel) {
        Session session = HibernateUtil.start();

        Hotel hotel2 = (Hotel) session.load(Hotel.class, hotel.getId());
        User user2 = (User) session.load(User.class, user.getId());

        hotel2.getUsers().add(user2);
        user2.getHotels().add(hotel2);

        // !!! cascade settings will also update the User
        session.update(hotel2);

        HibernateUtil.stop(true);
    }

    @Override
    public void addClient(Client client, Hotel hotel) {
        Session session = HibernateUtil.start();

        Hotel hotel2 = (Hotel) session.load(Hotel.class, hotel.getId());
        Client client2 = (Client) session.load(Client.class, client.getId());

        hotel2.getClients().add(client2);
        client2.getHotels().add(hotel2);

        // !!! cascade settings will also update the Client
        session.update(hotel2);

        HibernateUtil.stop(true);
    }

    @Override
    public void addRoom(Room room, Hotel hotel) {
        Session session = HibernateUtil.start();

        Hotel hotel2 = (Hotel) session.load(Hotel.class, hotel.getId());
        Room room2 = (Room) session.load(Room.class, room.getId());

        hotel2.getRooms().add(room2);

        session.update(hotel2);

        HibernateUtil.stop(true);
    }


}
