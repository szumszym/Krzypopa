package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.AdditionDAO;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:11
 */
public class AdditionDAOImpl extends BaseDAOImpl<Addition, Long> implements AdditionDAO {

    @Override
    public List<Addition> getAdditions(Long hotelId) {
        List<Addition> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Addition.class).addFilterIn("hotel.id", hotelId));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

    @Override
    public List<Addition> getAdditions(Hotel hotel) {
        return getAdditions(hotel.getId());
    }
}
