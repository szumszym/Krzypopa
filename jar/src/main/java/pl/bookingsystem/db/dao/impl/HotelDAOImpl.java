package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {
    @Override
    public List<Room> getRooms(Long hotel_id) {
        return selectMany("select h.rooms from Hotel h where h.id ="+String.valueOf(hotel_id));
    }

    public List selectAllWithAddress() {
        return selectMany("from Hotel as h left join fetch h.address");
    }

    @Override
    public List selectAllHotelsOfUser(String userId) {
        return selectMany("select hotels from User as u left join u.hotels hotels where u.id=" + userId);
    }

    @Override
    public List selectAllHotelsOfUser(Long userId) {
        return selectAllHotelsOfUser(String.valueOf(userId));
    }

    @Override
    public Hotel selectByID(String hotelId){
        return selectByID(Hotel.class, Long.valueOf(hotelId));
    }

    @Override
    public void addRoom(Room room, Hotel hotel) {
        Session session = HibernateUtil.start();
        hotel.getRooms().add(room);
        session.save(hotel);
        HibernateUtil.stop(true);
    }


}
