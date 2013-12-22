package pl.bookingsystem.common;

import pl.bookingsystem.db.dao.*;
import pl.bookingsystem.db.dao.impl.*;
import pl.bookingsystem.db.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddToDB {

    public static void addHotel() {
        HotelDAO hotelManager = new HotelDAOImpl();
        Address client_address = new Address("Wrocław", "Swidnicka", 120, 10, "44-450", "Polska");
        Date register_date = new Date();
        Set<Client> clients = new HashSet<Client>();
        clients.add(new Client("Jan", "Nowak", 85010101234L, "jan.nowak@gmail.com", "791234123", "pass", client_address, register_date));

        UserDAO userManager = new UserDAOImpl();
        List list = userManager.selectAll(User.class);
        Set<User> users = new HashSet<User>(list);


        Address hotel_address = new Address("Krakow", "Lubicz", 1, "30-200", "Polska");
        Hotel hotel = new Hotel("Hotel BLABLA", "+48 0123456789", "kontakt@hotelblabla.pl", hotel_address, clients, users);
        hotelManager.save(hotel);

        hotel.addRoom(createRoom(1));
        hotel.addRoom(createRoom(2));
        hotel.addRoom(createRoom(3));
        hotelManager.save(hotel);

        System.out.println("Rooms:");
        for (Room room : hotel.getRooms()) {
            System.out.println(room.getName());
        }

        RoomDAO roomManager = new RoomDAOImpl();
        List<Room> hotelRooms = roomManager.selectMany("select hotel.rooms from Hotel hotel where hotel.id = 30");
        System.out.println("Rooms:");
        for (Room room : hotelRooms) {
            System.out.println(room.getName() + " " + room.getHotel().getId());
        }


    }

    public static Room createRoom(int no) {
        // RoomDAO roomManager = new RoomDAOImpl();
        Room room = new Room(1, "Pokoj goscinny nr " + no, "2x1", 2);
        // roomManager.save(room);
        return room;
    }

    public static void addReservation() {

        HotelDAO hotelManager = new HotelDAOImpl();
        Hotel hotel = hotelManager.selectByID(Hotel.class, 1L);
        List<Room> rooms = hotelManager.getRooms(hotel.getId());

        Room room = rooms.get(1);

        ReservationDAO reservationManager = new ReservationDAOImpl();
        Address address = new Address("Wrocław", "Swidnicka", 120, 10, "44-450", "Polska");
        Date register_date = new Date();
        ClientDAO clientManager = new ClientDAOImpl();
        Client client = new Client("Jan", "Nowak", 85010101234L, "jan.nowak@gmail.com", "791234123", "pass", address, register_date);
        clientManager.save(client);
        Date todate = new Date();
        todate.setDate(30);

        StatusDAO statusManager = new StatusDAOImpl();
        Status status = statusManager.selectByID(Status.class, 1L);

        Reservation reservation = new Reservation("Rezerwacja 1", new Date(), todate, 3, client, status);

        reservation.getRooms().add(room);
        reservationManager.save(reservation);
    }

    public static void addUser() {
        UserDAO userManager = new UserDAOImpl();
        User user = new User("Zenon", "Breszka", 90030801234L, "z@gmail.pl", "792011166", "admin", User.Type.ADMIN, new Address("Krakow", "Wadowicka", 6, "12-234", "Polska"));
        userManager.save(user);
        user = new User("Rysiu", "Hebel", 80030801234L, "r@gmail.pl", "888011166", "user", User.Type.EMPLOYEE, new Address("Wroclaw", "Wroclawska", 7, 2, "32-234", "Polska"));
        userManager.save(user);
    }

    public static void addStatuses() {
        StatusDAO statusManager = new StatusDAOImpl();
        Status waiting = new Status("Oczekuje", "Rezerwacja oczekuje na potwierdzenie");
        statusManager.save(waiting);
        Status confirmed = new Status("Potwierdzono", "Potwierdzono ale jeszcze nei zaplacono");
        statusManager.save(confirmed);
        Status paid = new Status("Zapłacono", "Gotowa rezerwacja");
        statusManager.save(paid);
        Status canceled = new Status("Anulowano", "Rezerwacja do usunięcia");
        statusManager.save(canceled);
    }
}
