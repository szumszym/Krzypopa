package pl.bookingsystem.db;

import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.entity.Client;


public class HibernateTest {

    private ClientDAO clientManager;
    private Client client;

/*
    @Before
    public void init() {
        clientManager = new ClientDAOImpl();
        client = new Client("Zenon", "Breszka", 90030801234L, 0L, "zbreszka@dupa.pl", "792011166", "admin", new Address("Krakow", "Wadowicka", 6, "12-234", "Polska"));
    }

    @Test
    public void shouldAddNewClientToDatabase() {

        //given

        //when
        clientManager.save(client);

        //then

        Client expected = clientManager.selectByID(Client.class, client.getId());
        Assert.assertEquals(expected.getId(), client.getId());
        Assert.assertEquals(expected.getPesel(), client.getPesel());
    }

    @After
    public void cleanUp() {
        clientManager.delete(client);
        client = null;
    }*/

}
