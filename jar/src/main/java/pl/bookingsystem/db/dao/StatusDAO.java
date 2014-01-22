package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Status;

import java.util.List;


public interface StatusDAO extends BaseDAO<Status, Long> {
    List<Status> getStatuses(Long hotelId);

    List<Status> getStatuses(Hotel hotel);
}
