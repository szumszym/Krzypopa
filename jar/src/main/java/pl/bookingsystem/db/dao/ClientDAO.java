package pl.bookingsystem.db.dao;

import pl.bookingsystem.db.entity.Client;

public interface ClientDAO extends GenericDAO<Client, Long> {

    public Client findByClientName(String name, String surname);

    public Client findClientByReservation();

}