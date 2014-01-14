package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.User;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:13
 */
public interface ReservationDAO extends GenericDAO<Reservation, Long> {
    List<Reservation> getClientReservations(Client client);

    List<Reservation> getUserReservations(User user);
}
