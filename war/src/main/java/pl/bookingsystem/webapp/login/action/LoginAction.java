package pl.bookingsystem.webapp.login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Namespace("/")
@ResultPath(value = "/")
public class LoginAction extends ActionSupport implements SessionAware {

    private String login;
    private String password;
    private String hotelname;

    private Map<String, Object> session;


    @Action(value = "dashboard", results = {
            @Result(name = "adminlogged", type = "chain", location = "dashboard", params = {"namespace", "admin/"}),
            @Result(name = "employeelogged", type = "chain", location = "dashboard", params = {"namespace", "employee/"}),
            @Result(name = "ownerlogged", type = "chain", location = "dashboard", params = {"namespace", "owner/"}),
            @Result(name = "clientlogged", type = "chain", location = "dashboard", params = {"namespace", "client/"}),

            @Result(name = "error", location = "/modules/login/register_user.jsp")  //TODO: incorrect user or pass moze byc wyswietlany na tej samej stronie
    })
    public String login() {
        session.clear();
        session.put("isAdmin", false);
        session.put("isEmployee", false);
        session.put("isOwner", false);

        session.put("isUser", false);
        session.put("isClient", false);


        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.checkRegisteredUser(login, password);
        HotelDAO hotelDAO = new HotelDAOImpl();


        if (user != null) {

            User.Type userType = user.getType();
            session.put("login", getLogin());
            session.put("user", user);

            if (User.Type.ADMIN.equals(userType)) {
                session.put("isAdmin", true);
                session.put("isUser", true);
                session.put("admin", user);

                List<Hotel> hotels = hotelDAO.selectAllWithAddress();
                saveHotelsToSession(hotels);

                return "adminlogged";

            } else if (User.Type.EMPLOYEE.equals(userType)) {
                session.put("isEmployee", true);
                session.put("isUser", true);
                session.put("employee", user);

                List<Hotel> hotels = hotelDAO.selectAllHotelsOfUser(user.getId());
                saveHotelsToSession(hotels);

                return "employeelogged";

            } else if (User.Type.OWNER.equals(userType)) {
                session.put("isOwner", true);
                session.put("isUser", true);
                session.put("owner", user);

                List<Hotel> hotels = hotelDAO.selectAllHotelsOfUser(user.getId());
                saveHotelsToSession(hotels);

                return "ownerlogged";
            }
        } else {
            ClientDAO clientDAO = new ClientDAOImpl();
            Client client = clientDAO.checkRegisteredClient(login, password);
            if (client != null) {
                session.put("isClient", true);
                session.put("client", client);

                List<Hotel> hotels = hotelDAO.selectAllWithAddress();
                saveHotelsToSession(hotels);

                return "clientlogged";
            }

        }
        return ERROR;
    }

    private void saveHotelsToSession(List<Hotel> hotels) {
        if (hotels.size() > 0) {
            session.put("hotels", hotels);
            Hotel hotel = null;
            if (!hotels.isEmpty()) {
                hotel = hotels.get(0);
                if (hotel != null) {
                    hotelname = hotel.getName();
                }
            }
            session.put("hotel", hotel);
        } else {

            session.put("hotels", new LinkedList<Room>());
            session.put("hotel", null);
            hotelname = "You don't have any hotels!";
        }
    }

    @Action(value = "logout", results = {@Result(name = "success", location = "/modules/login/logout.jsp")})
    public String logout() {
       // stopAll();
        session.clear();
        return SUCCESS;
    }

    @Action(value = "login", results = {@Result(name = "success", location = "/modules/login/login.jsp")})
    public String goToLogin() {
        return SUCCESS;
    }

    @Action(value = "", results = {@Result(name = SUCCESS, location = "/modules/guest/index.jsp")})
    public String returnToStartPage() {
        return SUCCESS;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHotelname() {
        return hotelname;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}