package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Addition;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:07
 */
public interface AdditionDAO extends GenericDAO<Addition, Long> {

    List<Addition> getAdditionsBy(List<String> additions, String by);
}
