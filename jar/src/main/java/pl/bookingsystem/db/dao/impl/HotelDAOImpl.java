package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.util.List;

public class HotelDAOImpl extends BaseDAOImpl<Hotel, Long> implements HotelDAO {


    @Override
    public List<Hotel> selectAllWithAddress() {
        List<Hotel> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Hotel.class).addFetch("address"));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Hotel> selectAllWithUsers() {
        List<Hotel> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Hotel.class).addFetch("users"));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Hotel> selectAllHotelsOfUser(Long userId) {
        List<Hotel> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Hotel.class)
                    .addFetch("address")
                    .addFilterSome("users", Filter.equal("id", userId)));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Hotel> selectAllHotels() {
        List<Hotel> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = fetchAll();
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public Hotel selectByID(Long hotelId) {
        return selectByID(Hotel.class, hotelId);
    }

    @Override
    public void addRoom(Room room, Hotel hotel) {
        Hotel _hotel = selectWith(hotel.getId(), "rooms");
        _hotel.getRooms().add(room);
        update(_hotel);
    }

    @Override
    public void addAddition(Addition addition, Hotel hotel) {
        Hotel _hotel = selectWith(hotel.getId(), "additions");
        _hotel.getAdditions().add(addition);
        update(_hotel);
    }

    @Override
    public void addStatus(Status status, Hotel hotel) {
        Hotel _hotel = selectWith(hotel.getId(), "statuses");
        _hotel.getStatuses().add(status);
        update(_hotel);
    }

    @Override
    public List<Hotel> getHotelFromCity(String city) {
        List<Hotel> hotels;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            hotels = search(new Search().addFilterLike("address.city", "%" + city + "%"));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return hotels;
    }
}
