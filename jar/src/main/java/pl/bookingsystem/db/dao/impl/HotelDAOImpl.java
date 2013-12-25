package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Query query = session.createQuery("select h.rooms from hotel h where h.id = :hotel_id");
        query.setParameter("hotel_id", hotel_id);
        List<Room> rooms = (List<Room>) query.list();
        session.close();
        return rooms;
    }

    @Override
    public User getOwner(Long hotel_id){
        User owner = null;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("SELECT u FROM user u, hotel_user  hu WHERE hu.hotel_id = :hotel_id and u.type =\"OWNER\" ");
        query.setParameter("hotel_id", hotel_id);
        owner = (User)  query.uniqueResult();
        return owner;
    }

}
