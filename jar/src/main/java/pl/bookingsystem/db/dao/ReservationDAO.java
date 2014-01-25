package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.*;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:13
 */
public interface ReservationDAO extends BaseDAO<Reservation, Long> {
    List<Reservation> getClientReservations(Client client);

    List<Reservation> getHotelReservations(Hotel hotel);

    List<Reservation> getAllReservations();

    List<Reservation> getAllReservationsFrom(Room room);

    List<Reservation> getAllReservationsFrom(List<Room> rooms);

    List<Reservation> getAllReservationsWhichHas(Status status);
}
