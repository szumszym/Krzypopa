package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.entity.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.12.13 @ 14:02
 */
public class ReservationDAOImpl extends BaseDAOImpl<Reservation, Long> implements ReservationDAO {
    @Override
    public List<Reservation> getClientReservations(Client client) {
        List<Reservation> t;
        try {
            start();
            t = search(new Search(Reservation.class)
                    .addFetches("rooms", "rooms.hotel", "client")
                    .addFilterEqual("client", client));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public List<Reservation> getHotelReservations(Hotel hotel) {
        List<Reservation> t;
        try {
            start();
            t = search(new Search(Reservation.class)
                    .addFilterEqual("rooms.hotel", hotel)
                    .addFetch("rooms")
                    .addFetch("client")
                    .setDistinct(true));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> t;
        try {
            start();
            t = search(new Search(Reservation.class)
                    .addFetches("rooms", "rooms.hotel", "client")
                    .setDistinct(true));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservationsFrom(Room room) {
        List<Reservation> t;
        try {
            start();
            t = search(new Search(Reservation.class)
                    .addFilterSome("rooms", Filter.in(Filter.ROOT_ENTITY, room))
                    .setDistinct(true));
        } finally {
            stop();
        }
        return t;
    }


    @Override
    public List<Reservation> getAllReservationsFrom(List<Room> rooms) {
        List<Reservation> t = new LinkedList<Reservation>();
        for (Room room : rooms) {
            try {
                start();
                List<Reservation> roomReservations = search(new Search(Reservation.class)
                        .addFilterSome("rooms", Filter.in(Filter.ROOT_ENTITY, room))
                        .setDistinct(true));

                t.addAll(roomReservations);
            } finally {
                stop();
            }
        }
        return t;
    }

    @Override
    public List<Reservation> getAllReservationsWhichHas(Status status) {
        List<Reservation> t;
        try {
            start();
            t = search(new Search(Reservation.class)
                    .addFilterSome("status", Filter.in(Filter.ROOT_ENTITY, status))
                    .setDistinct(true));
        } finally {
            stop();
        }
        return t;
    }
}
