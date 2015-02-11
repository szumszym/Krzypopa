package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Search;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.entity.Client;

import java.util.List;

public class ClientDAOImpl extends BaseDAOImpl<Client, Long> implements ClientDAO {
    public ClientDAOImpl() {
        super("/hibernate-mysql2.cfg.xml");
    }

    @Override
    public Client checkRegisteredClient(String email, String password) {
        Client client;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            client = (Client) searchUnique(new Search(Client.class)/*.setResultMode(ISearch.RESULT_SINGLE)*/
                    .addFilterEqual("email", email)
                    .addFilterEqual("password", password));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return client;
    }

    @Override
    public boolean checkIfEmailIsInDB(String email) {
        Client client;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            client = (Client) searchUnique(new Search(Client.class)/*.setResultMode(ISearch.RESULT_SINGLE)*/
                    .addFilterEqual("email", email));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return client != null;
    }

    @Override
    public List<Client> findByClientName(String name, String surname) {
        List<Client> t;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = search(new Search(Client.class)
                    .addFilterEqual("first_name", name)
                    .addFilterEqual("last_name", surname));
        } finally {
            if(session!= null && session.isOpen())session.close();
        }
        return t;
    }

}