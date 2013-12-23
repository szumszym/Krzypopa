package pl.bookingsystem.db.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.utils.HibernateUtil;

/**
 * Author: rastek
 * Date: 22.12.13 @ 14:02
 */
public class ReservationDAOImpl extends GenericDAOImpl<Reservation, Long> implements ReservationDAO {

    @Override
    public Reservation save(Reservation entity) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();
        session.saveOrUpdate(entity);

        session.getTransaction().commit();

        session.close();

        return entity;
    }

}
