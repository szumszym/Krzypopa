package pl.bookingsystem.webapp.guest.action;


import org.junit.Before;
import org.junit.Test;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FirstStepActionTest {

    private FirstStepAction firstStepAction;
    private RoomDAO roomDAO;
    private ReservationDAO reservationDAO;

    @Before
    public void setUp(){
        roomDAO = mock(RoomDAOImpl.class);
        reservationDAO = mock(ReservationDAOImpl.class);
        firstStepAction = new FirstStepAction(roomDAO, reservationDAO);
    }

    @Test
    public void shouldReturnListWithFreeRoomsWhenNoReservation(){
        //given
        Date from = new Date();
        Date to = new Date();
        String city = "";
        List<Room> rooms = new ArrayList<Room>(){{
            Room room = new Room();
            room.setPrice(100.0);
            room.setHotel(new Hotel());

            add(room);
            add(room);
            add(room);
        }};

        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        when(roomDAO.getRoomsFromCity(anyString())).thenReturn(rooms);
        when(reservationDAO.getAllReservationsFrom(rooms)).thenReturn(reservations);


        //when
        List<String[]> freeRooms = firstStepAction.getFreeRooms(from, to, city);

        //then
        assertEquals("should return 3 rooms", freeRooms.size(), 3);
    }

    @Test
    public void shouldReturn2FreeRoomsWhenOneReservation(){
        //given
        final Date from = new Date();
        final Date to = new Date();
        String city = "";
        final Room freeRoom = aRoom();
        final Room reservedRoom = aRoom();

        ArrayList<Reservation> reservations = new ArrayList<Reservation>() {{
            add(aReservation(from, to, reservedRoom));
        }};

        List<Room> rooms = new ArrayList<Room>(){{
            add(freeRoom);
            add(reservedRoom);
            add(freeRoom);
        }};

        when(roomDAO.getRoomsFromCity(anyString())).thenReturn(rooms);
        when(reservationDAO.getAllReservationsFrom(reservedRoom)).thenReturn(reservations);


        //when
        List<String[]> freeRooms = firstStepAction.getFreeRooms(from, to, city);

        //then
        assertEquals("should return 3 rooms", freeRooms.size(), 2);
    }

    private Reservation aReservation(Date from, Date to, final Room reservedRoom) {
        return new Reservation("name",from, to, 1, new Status(), new ArrayList<Room>(){{add(reservedRoom);}}, 100.0);
    }

    private Room aRoom() {
        final Room room = new Room();
        room.setPrice(100.0);
        room.setHotel(new Hotel());
        return room;
    }
}