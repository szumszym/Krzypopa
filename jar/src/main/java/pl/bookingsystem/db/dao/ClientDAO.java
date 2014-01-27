package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Client;

import java.util.List;

public interface ClientDAO extends BaseDAO<Client, Long> {

    Client checkRegisteredClient(String email, String password);

    boolean checkIfEmailIsInDB(String email);

    public List<Client> findByClientName(String name, String surname);

    public List<Client> getClientsFromHotel(Long hotelId);

}