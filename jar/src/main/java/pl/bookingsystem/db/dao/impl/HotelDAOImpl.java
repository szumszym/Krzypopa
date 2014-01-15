package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {
    @Override
    public List<Room> getRooms(Long hotel_id) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("select h.rooms from Hotel h where h.id = :hotel_id");
        query.setParameter("hotel_id", hotel_id);
        List<Room> rooms = (List<Room>) query.list();
        session.close();
        return rooms;
    }

    public List selectAllWithAddress() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<? extends Object> list = null;
        Query query = session.createQuery("from Hotel as h left join fetch h.address");
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }
    @Override
    public void addRoom(Room room, Hotel hotel) {
        Session session = HibernateUtil.start();
        hotel.getRooms().add(room);
        session.save(hotel);
        HibernateUtil.stop(true);
    }


}
