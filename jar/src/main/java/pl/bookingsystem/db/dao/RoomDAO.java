package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Room;

import java.util.List;

public interface RoomDAO extends GenericDAO<Room, Long> {

    void deleteByID(Long id);

    public List getRoomsFromCity(String city);
}
