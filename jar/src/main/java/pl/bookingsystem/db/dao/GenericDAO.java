package pl.bookingsystem.db.dao;

import org.hibernate.Query;
import pl.bookingsystem.db.entity.Room;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface GenericDAO<T, ID extends Serializable> {

    T save(T entity);      //save or update

    T merge(T entity);

    void deleteByID(String table, ID id);

    void delete(T entity);

    List selectAll(Class clazz);   //select * from clazz

    List<T> selectMany(Query query);

    List selectMany(String hql);

    T selectOne(Query query);

    T selectOne(String hql);

    T selectByID(Class clazz, ID id);

    List selectByIDS(Class clazz, List<String> ids);
}