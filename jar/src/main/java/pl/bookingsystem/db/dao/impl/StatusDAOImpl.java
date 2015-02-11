package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Search;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Status;

import java.util.List;

/**
 * Author: rastek
 * Date: 22.12.13 @ 17:03
 */
public class StatusDAOImpl extends BaseDAOImpl<Status, Long> implements StatusDAO {

    @Override
    public List<Status> getStatuses(Long hotelId) {
        List<Status> t;
        try {
            start();
            t = search(new Search(Status.class)
                    .addFilterIn("hotel.id", hotelId));
        } finally {
            stop();
        }
        return t;
    }

    @Override
    public List<Status> getStatuses(Hotel hotel) {
        return getStatuses(hotel.getId());
    }

}
