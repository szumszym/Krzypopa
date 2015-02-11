package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:13
 */
public interface ReservationDAO extends BaseDAO<Reservation, Long> {
    List<Reservation> getClientReservations(Long clientId);

    List<Reservation> getHotelReservations(Hotel hotel);

    List<Reservation> getAllReservations();

    List<Reservation> getAllReservationsFrom(Room room);

    List<Reservation> getAllReservationsFrom(List<Room> rooms);

    List<Reservation> getAllReservationsWhichHas(Status status);
}
