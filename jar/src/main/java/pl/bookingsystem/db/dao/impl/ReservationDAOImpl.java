package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

/**
 * Author: rastek
 * Date: 22.12.13 @ 14:02
 */
public class ReservationDAOImpl extends GenericDAOImpl<Reservation, Long> implements ReservationDAO {
    @Override
    public List<Reservation> getClientReservations(Client client) {
        String sql = "select r, r.client.email, room.no_room, room.hotel.id  FROM Reservation as r join r.rooms as room where :client in (r.client)";

        Session session = HibernateUtil.start();
        Query query = session.createQuery(sql);
        query.setParameter("client", client);

        List<Reservation> reservations = query.list();
        HibernateUtil.stop();

        return reservations;
    }

    @Override
    public List<Reservation> getUserReservations(User user) {
        String sql = "select r, r.client.email, rooms.no_room, hotel.id FROM Reservation as r join r.rooms as rooms join rooms.hotel as hotel where hotel.id in (select h.id from Hotel as h join h.users as u where u = :user)";

        Session session = HibernateUtil.start();
        Query query = session.createQuery(sql);
        query.setParameter("user", user);

        List<Reservation> reservations = query.list();
        HibernateUtil.stop();

        return reservations;
    }

    @Override
    public List<Reservation> getHotelReservations(Hotel hotel) {
        String sql = "select r, r.client.email, rooms.no_room, hotel.id FROM Reservation as r join r.rooms as rooms join rooms.hotel as hotel where hotel= :hotel";

        Session session = HibernateUtil.start();
        Query query = session.createQuery(sql);
        query.setParameter("hotel", hotel);

        List<Reservation> reservations = query.list();
        HibernateUtil.stop();

        return reservations;
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "select r, r.client.email, rooms.no_room, hotel.id FROM Reservation as r join r.rooms as rooms join rooms.hotel as hotel";

        Session session = HibernateUtil.start();
        Query query = session.createQuery(sql);

        List<Reservation> reservations = query.list();
        HibernateUtil.stop();

        return reservations;
    }
}
