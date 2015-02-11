package pl.bookingsystem.db.dao.impl;


import pl.bookingsystem.db.dao.ClientAddressDAO;
import pl.bookingsystem.db.entity.ClientAddress;

public class ClientAddressDAOImpl extends BaseDAOImpl<ClientAddress, Long> implements ClientAddressDAO {
    public ClientAddressDAOImpl() {
        super("/hibernate-mysql2.cfg.xml");
    }
}
