package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;

import java.util.List;

/**
 * Author: rastek
 * Date: 19.12.13 @ 00:39
 */
public class RoomDAOImpl extends BaseDAOImpl<Room, Long> implements RoomDAO {

    @Override
    public List<Room> getRooms(Long hotelId) {
        List<Room> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Room.class)
                    .addFilterIn("hotel.id", hotelId));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Room> getRooms(Hotel hotel) {
        return getRooms(hotel.getId());
    }

    @Override
    public List<Room> getRoomsFromCity(String city) {
        List<Room> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Room.class)
                    .addFetch("hotel")
                    .addFilterLike("hotel.address.city", "%" + city + "%"));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }


    @Override
    public List<Room> getAllRoomsWhichHave(Addition addition) {
        List<Room> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Room.class)
                    .addFilterSome("additions", Filter.in(Filter.ROOT_ENTITY, addition))
                    .setDistinct(true));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

}
