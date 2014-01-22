package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:07
 */
public interface AdditionDAO extends BaseDAO<Addition, Long> {

    List<Addition> getAdditions(Long hotelId);

    List<Addition> getAdditions(Hotel hotel);
}
