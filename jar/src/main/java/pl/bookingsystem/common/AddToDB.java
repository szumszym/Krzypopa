package pl.bookingsystem.common;

import pl.bookingsystem.db.dao.*;
import pl.bookingsystem.db.dao.impl.*;
import pl.bookingsystem.db.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddToDB {

    public static void addHotel(String name, Long userId) {
        HotelDAO hotelManager = new HotelDAOImpl();
        Address client_address = new Address("Wrocław", "Swidnicka", 120, 10, "44-450", "Polska");
        Date register_date = new Date();
        Set<Client> clients = new HashSet<Client>();
        clients.add(new Client("Jan", "Nowak", 85010101234L, "jan.nowak@gmail.com", "791234123", "pass", client_address, register_date));

        UserDAO userManager = new UserDAOImpl();
        User user = userManager.selectByID(User.class, userId);
        Set<User> users = new HashSet<User>();
        users.add(user);


        Address hotel_address = new Address("Krakow", "Lubicz", 1, "30-200", "Polska");
        Hotel hotel = new Hotel(name, "+48 0123456789", "kontakt@hotelblabla.pl", hotel_address, clients, users);
        hotelManager.save(hotel);

        hotelManager.addRoom(createRoom(1, hotel), hotel);
        hotelManager.addRoom(createRoom(2, hotel), hotel);
        hotelManager.addRoom(createRoom(3, hotel), hotel);
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

    public static Room createRoom(int no, Hotel hotel) {
        // RoomDAO roomManager = new RoomDAOImpl();
        Room room = new Room(1, "Pokoj goscinny nr " + no, "2x1", 2, hotel, 0.0 );
        // roomManager.save(room);
        return room;
    }

    public static void addReservation() {

        HotelDAO hotelManager = new HotelDAOImpl();
        Hotel hotel = hotelManager.selectByID(Hotel.class, 2L);
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

        Reservation reservation = new Reservation("Rezerwacja 1", new Date(), todate, 3, client, status, rooms, 10.50);

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
        Status waiting = new Status("Oczekuje", "Rezerwacja oczekuje na potwierdzenie", "red");
        statusManager.save(waiting);
        Status confirmed = new Status("Potwierdzono", "Potwierdzono ale jeszcze nei zaplacono", "blue");
        statusManager.save(confirmed);
        Status paid = new Status("Zapłacono", "Gotowa rezerwacja", "yellow");
        statusManager.save(paid);
        Status canceled = new Status("Anulowano", "Rezerwacja do usunięcia", "green");
        statusManager.save(canceled);
    }

    public static void getHotelsRooms(Long hotelId) {

        HotelDAO hotelManager = new HotelDAOImpl();
        List<Room> rooms = hotelManager.getRooms(hotelId);
        for (Room room : rooms) {
            System.out.println(room.getId() + " " + room.getName());
        }
    }

    public static void deleteRoomByID(String table, Long id) {
        RoomDAO roomManager = new RoomDAOImpl();
        roomManager.deleteByID(table, id);
    }

    public static void deleteRoom() {  //TODO: not working!!!
        HotelDAO hotelManager = new HotelDAOImpl();
        Hotel hotel = hotelManager.selectByID(Hotel.class, 3L);
        List<Room> rooms = hotelManager.getRooms(hotel.getId());

        Room room = rooms.get(1);
        RoomDAO roomDAO = new RoomDAOImpl();
        roomDAO.delete(room);
    }

    public static void getReservation() {
        ReservationDAO reservationManager = new ReservationDAOImpl();
        List<Reservation> reservations = reservationManager.selectAll(Reservation.class);

        int size = reservations.size();
        String[][] data = new String[size][];
        for (int j = 0; j < reservations.size(); j++) {
            String[] reservation = new String[8];
            Reservation r = reservations.get(j);
            System.out.println(String.valueOf(r.getId()));
            reservation[0] = String.valueOf(r.getId());
            reservation[1] = String.valueOf(r.getName());
            reservation[2] = String.valueOf(r.getDate_from());
            reservation[3] = String.valueOf(r.getDate_to());
            reservation[4] = String.valueOf(r.getPerson_count());
            reservation[5] = String.valueOf(r.getStatus());
            reservation[6] = String.valueOf(r.getEntry_date());
            reservation[7] = String.valueOf(r.getUpdate_date() != null ? r.getUpdate_date() : "-");

            data[j] = reservation;
        }
    }

    public static void getHotels() {
        HotelDAO hotelManager = new HotelDAOImpl();
        List<Hotel> hotel = hotelManager.selectAllWithAddress();

    }
}
