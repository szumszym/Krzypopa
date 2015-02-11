package pl.bookingsystem.db.dao;


import org.junit.Before;
import org.junit.Test;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.User;

import static org.junit.Assert.*;

public class UserDAOTest {

    UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAOImpl();
        createTableUser();
    }

    @Test
    public void shouldReturnNullWhenWrongPassword() {
        //given
        User user = getUser("email", "pass");
        userDAO.create(user);

        //when
        User returnedUser = userDAO.checkRegisteredUser("email", "pass2");

        //then
        assertNull(returnedUser);
    }

    @Test
    public void shouldReturnNullWhenWrongEmail() {
        //given
        User user = getUser("email", "pass");
        userDAO.create(user);

        //when
        User returnedUser = userDAO.checkRegisteredUser("email2", "pass");

        //then
        assertNull(returnedUser);
    }

    @Test
    public void shouldReturnUserObjectWhenCorrectPasswordAndCorrectEmail() {
        //given
        User user = getUser("email", "pass");
        userDAO.create(user);

        //when
        User returnedUser = userDAO.checkRegisteredUser("email", "pass");

        //then
        assertNotNull(returnedUser);
        assertEquals("email", returnedUser.getEmail());
        assertEquals("pass", returnedUser.getPassword());
    }

    private User getUser(String email, String pass) {
        return new User("Jan", "Kowalski", 88101001111L, email, "123123123", pass, User.Type.ADMIN, new Address("1", "1", "1", "1", "1"));
    }

    private void createTableUser() {
        userDAO.start()
                .createSQLQuery("CREATE TABLE IF NOT EXISTS `user` (\n" +
                        "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                        "  `first_name` varchar(255) NOT NULL,\n" +
                        "  `last_name` varchar(255) NOT NULL,\n" +
                        "  `pesel` bigint(20) NOT NULL,\n" +
                        "  `nip` bigint(20) DEFAULT NULL,\n" +
                        "  `email` varchar(255) NOT NULL,\n" +
                        "  `address_id` bigint(20) NOT NULL,\n" +
                        "  `phone_number` varchar(25) NOT NULL,\n" +
                        "  `pass` varchar(255) NOT NULL,\n" +
                        "  `user_type` varchar(40) NOT NULL DEFAULT 'EMPLOYEE',\n" +
                        "  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                        "  `update_date` timestamp NULL DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  KEY `FK_dhlcfg8h1drrgu0irs1ro3ohb` (`address_id`)\n" +
                        ");")
                .executeUpdate();
        userDAO.stop(true);
    }

}