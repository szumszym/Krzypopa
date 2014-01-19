package pl.bookingsystem.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

/**
 * Author: rastek
 * Date: 19.12.13 @ 00:39
 */
public class RoomDAOImpl extends GenericDAOImpl<Room, Long> implements RoomDAO {

    @Override
    public void deleteByID(Long id) {
        deleteByID("Room", id);
    }

    @Override
    public List getRoomsFromCity(String city){
        return selectMany("select r, r.hotel.id, r.hotel.name from Room r where lower(r.hotel.address.city) like '%"+city+"%'");
    }

}
