package pl.bookingsystem.db.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

public class ClientDAOImpl extends GenericDAOImpl<Client, Long> implements ClientDAO {

    private static Logger logger = Logger.getLogger(ClientDAOImpl.class);


    @Override
    public Client checkRegisteredClient(String clientname, String password) {

        try {
            Session session = HibernateUtil.start(true);
            String sql = "SELECT c FROM Client c WHERE c.email = :email AND c.password = :password";
            Query query = session.createQuery(sql);
            query.setParameter("email", clientname).setParameter("password", password);

            Client client = (Client) query.uniqueResult();
            if (client != null) return client;

        } catch (NonUniqueResultException ex) {
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            System.out.println("FIND Client.java: " + ex.getMessage());
        } finally {
            HibernateUtil.stop(false);
        }
        return null;
    }


    public Client findByClientName(String name, String surname) {
        Client client = null;
        Session session = HibernateUtil.start(true);
        try {
            String sql = "SELECT p FROM Client p WHERE p.first_name = :first_name AND p.last_name = :last_name";

            Query query = session.createQuery(sql);
            query.setParameter("first_name", name).setParameter("last_name", surname);

            client = (Client) query.uniqueResult();
            HibernateUtil.stop(false);

        } catch (NonUniqueResultException ex) {
            logger.error("FIND Client.java: " + ex.getMessage());
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            logger.error("FIND Client.java: " + ex.getMessage());
        }
        return client;
    }

    @Override
    public List getClientsFromHotel(Long hotelId){
        return selectMany("select c from Client c, Hotel h where c in elements(h.clients) and h.id="+hotelId.toString());
    }
}