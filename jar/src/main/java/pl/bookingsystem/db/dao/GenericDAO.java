package pl.bookingsystem.db.dao;

import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    T save(T entity);      //save or update

    T merge(T entity);

    T saveOrUpdat(T entity);

    void delete(T entity);

    List selectAll(Class clazz);   //select * from clazz

    List<T> selectMany(Query query);

    List<T> selectMany(String hql);

    T selectOne(Query query);

    T selectOne(String hql);

    T selectByID(Class clazz, ID id);

}