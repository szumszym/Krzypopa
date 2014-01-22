package pl.bookingsystem.common;

import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;

import java.util.List;

/**
 * Author: rastek
 * Date: 21.01.14 @ 23:30
 */
public class GenericDAOTest {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAOImpl();
        List<Client> clientsFromHotel = clientDAO.getClientsFromHotel(1L);
        for(Client c : clientsFromHotel) {
            System.out.println(c.getEmail());
        }
        Client c = (Client) clientDAO.findByClientName("Jan", "Nowak").get(0);
        System.out.println(c.getEmail());

        Client c2 = clientDAO.checkRegisteredClient("jan.nowak@gmail.com", "jan");

      //  System.out.println(c2.getEmail());


        HotelDAO hotelDAO = new HotelDAOImpl();
        List<Hotel> hotels = hotelDAO.selectAllHotelsOfUser(2L);
        Hotel hotel1 = hotels.get(0);
        for(Hotel h: hotels) {
        //    System.out.println(h.getEmail());
        }


       // User user = new User("Nowy", "User", 90020202211L, "nowy.user@gmail.pl", "+22 23423123", "nowy", User.Type.EMPLOYEE, new Address("Brzeg", "Krótka", 23, 3, "11-222", "Poland"));

        RoomDAO roomDAO = new RoomDAOImpl();
        List<Room> rooms = roomDAO.getRoomsFromCity("kr");
        for( Room r: rooms){
            System.out.println(r.getName());

        }

        System.exit(0);
    }
}
