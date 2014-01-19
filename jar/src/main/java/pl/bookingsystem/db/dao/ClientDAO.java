package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Client;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client, Long> {

    Client checkRegisteredClient(String clientname, String password);

    public Client findByClientName(String name, String surname);

    public List getClientsFromHotel(Long hotelId);

}