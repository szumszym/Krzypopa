package pl.bookingsystem.db.dao.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.entity.Client;

import java.util.List;

public class ClientDAOImpl extends BaseDAOImpl<Client, Long> implements ClientDAO {

    @Override
    public Client checkRegisteredClient(String email, String password) {
        Client client;
        try{
            start();
            client = (Client) searchUnique(new Search(Client.class)/*.setResultMode(ISearch.RESULT_SINGLE)*/
                    .addFilterEqual("email", email)
                    .addFilterEqual("password", password));
        } finally {
            stop(false);
        }
        return client;
    }

    @Override
    public List<Client> findByClientName(String name, String surname) {
        List<Client> t;
        try{
            start();
            t = search(new Search(Client.class)
                    .addFilterEqual("first_name", name)
                    .addFilterEqual("last_name", surname));
        } finally {
            stop(false);
        }
        return t;
    }

    @Override
    public List<Client> getClientsFromHotel(Long hotelId){
        List<Client> t;
        try{
            start();
            t = search(new Search(Client.class)
                    .addFilterSome("hotels", Filter.equal("id", hotelId)));
        } finally {
            stop(false);
        }
        return t;
      }
}