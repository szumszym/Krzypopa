package pl.bookingsystem.db.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.utils.HibernateUtil;

public class ClientDAOImpl extends GenericDAOImpl<Client, Long> implements ClientDAO {

    private static Logger logger = Logger.getLogger(ClientDAOImpl.class);

    public Client findByClientName(String name, String surname) {
        Client client = null;
        try {
            String sql = "SELECT p FROM Client p WHERE p.first_name = :first_name AND p.last_name = :last_name";
            Session session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery(sql);
            query.setParameter("first_name", name).setParameter("last_name", surname);

            client = selectOne(query);

        } catch (NonUniqueResultException ex) {
            logger.error("FIND Client.java: " + ex.getMessage());
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            logger.error("FIND Client.java: " + ex.getMessage());
        }
        return client;
    }


    public Client findClientByReservation() {
        Client client = null;

        return client;
    }
}