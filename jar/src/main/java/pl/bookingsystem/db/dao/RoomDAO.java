package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;

import java.util.List;

public interface RoomDAO extends BaseDAO<Room, Long> {

    List<Room> getRooms(Long id);

    List<Room> getRooms(Hotel hotel);

    public List<Room> getRoomsFromCity(String city);

    List<Room> getAllRoomsWhichHave(Addition addition);
}
