package pl.bookingsystem.db.dao;


import com.googlecode.genericdao.dao.hibernate.original.GenericDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.01.14 @ 12:43
 */
public interface BaseDAO<T, ID extends Serializable> extends GenericDAO<T, ID> {

    T selectByID(Class<T> clazz, Long id);

    List<T> selectByIDs(Class<T> clazz, List<Long> ids);

    List<T> selectAll(Class<T> clazz);

    void create(T t);

    List<T> selectWith(String property);

    T selectWith(ID id, String property);

    void deleteByID(ID id);

    void delete(T object);
}
