package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.12.13 @ 14:02
 */
public class ReservationDAOImpl extends BaseDAOImpl<Reservation, Long> implements ReservationDAO {
    @Override
    public List<Reservation> getClientReservations(Long client) {
        List<Reservation> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Reservation.class)
                    .addFetches("rooms", "rooms.hotel")
                    .addFilterEqual("clientId", client));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Reservation> getHotelReservations(Hotel hotel) {
        List<Reservation> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Reservation.class)
                    .addFilterEqual("rooms.hotel", hotel)
                    .addFetch("rooms")
                    /*.addFetch("client")*/
                    .setDistinct(true));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Reservation.class)
                    .addFetches("rooms", "rooms.hotel"/*, "client"*/)
                    .setDistinct(true));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservationsFrom(Room room) {
        List<Reservation> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Reservation.class)
                    .addFilterSome("rooms", Filter.in(Filter.ROOT_ENTITY, room))
                    .setDistinct(true));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }


    @Override
    public List<Reservation> getAllReservationsFrom(List<Room> rooms) {
        List<Reservation> t = new LinkedList<Reservation>();
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (Room room : rooms) {
            try {
                List<Reservation> roomReservations = search(new Search(Reservation.class)
                        .addFilterSome("rooms", Filter.in(Filter.ROOT_ENTITY, room))
                        .setDistinct(true));

                t.addAll(roomReservations);
            } finally {
                if(session!= null && session.isOpen())session.close();
            }
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservationsWhichHas(Status status) {
        List<Reservation> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Reservation.class)
                    .addFilterSome("status", Filter.in(Filter.ROOT_ENTITY, status))
                    .setDistinct(true));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }
}
