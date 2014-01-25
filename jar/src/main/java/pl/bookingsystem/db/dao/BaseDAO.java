package pl.bookingsystem.db.dao;


import com.googlecode.genericdao.dao.hibernate.original.GenericDAO;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Author: rastek
 * Date: 22.01.14 @ 12:43
 */
public interface BaseDAO<T, ID extends Serializable> extends GenericDAO<T, ID> {

    Session start();

    void stop(boolean withCommit);

    T selectByID(Class<T> clazz, Long id);

    List<T> selectByIDs(Class<T> clazz, List<Long> ids);

    List<T> selectAll(Class<T> clazz);

    void create(T t);

    void deleteOnly(T object);

    List<T> selectWith(String property);

    T selectWith(ID id, String property);

    void deleteByID(ID id);

    void delete(T object);
}
