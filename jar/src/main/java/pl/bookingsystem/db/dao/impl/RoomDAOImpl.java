package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

/**
 * Author: rastek
 * Date: 19.12.13 @ 00:39
 */
public class RoomDAOImpl extends GenericDAOImpl<Room, Long> implements RoomDAO {
    @Override
    public List<Room> getReservations() {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Room r where r.reservations = :hotel_id");
        List<Room> rooms = (List<Room>) query.list();
        session.close();
        return rooms;
    }

    @Override
    public void deleteByID(Long id) {
        deleteByID("Room", id);
    }

}
