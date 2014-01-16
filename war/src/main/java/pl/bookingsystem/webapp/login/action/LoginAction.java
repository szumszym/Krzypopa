package pl.bookingsystem.webapp.login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.User;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.Map;


@Namespace("/")
@ResultPath(value = "/")
public class LoginAction extends ActionSupport implements SessionAware {

    private String login;
    private String password;

    private Map<String, Object> session;


    @Action(value = "dashboard", results = {
            @Result(name = "adminlogged", type="chain", location = "dashboard", params = {"namespace", "admin/"}),
            @Result(name = "employeelogged", type="chain", location = "dashboard", params = {"namespace", "employee/"}),
            @Result(name = "ownerlogged", type="chain", location = "dashboard", params = {"namespace", "owner/"}),
            @Result(name = "clientlogged", type="chain", location = "dashboard", params = {"namespace", "client/"}),

            @Result(name = "error", location = "/modules/login/register_user.jsp")  //TODO: incorrect user or pass moze byc wyswietlany na tej samej stronie
    })
    public String login() {
        session.clear();

        UserDAO userManager = new UserDAOImpl();
        User user = userManager.checkRegisteredUser(login, password);

        if (user != null) {

            User.Type userType = user.getType();
            session.put("login", getLogin());
            session.put("user", user);

            if (User.Type.ADMIN.equals(userType)) {
                //TODO: get all hotels
                session.put("isAdmin", true);
                session.put("admin", user);
                return "adminlogged";
            } else if (User.Type.EMPLOYEE.equals(userType)) {
                //TODO: get one hotel in which employee works
                session.put("isEmployee", true);
                session.put("employee", user);
                return "employeelogged";
            } else if (User.Type.OWNER.equals(userType)) {
                //TODO: get all hotels of owner
                session.put("isOwner", true);
                session.put("owner", user);
                return "ownerlogged";
            }
        } else {
            ClientDAO clientManager = new ClientDAOImpl();
            Client client = clientManager.checkRegisteredClient(login, password);
            if (client != null) {
                session.put("isClient", true);
                session.put("client", client);
                return "clientlogged";
            }

        }
        return ERROR;
    }

    @Action(value = "logout", results = {@Result(name = "success", location = "/modules/login/logout.jsp")})
    public String logout() {
        HibernateUtil.stopAll();
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}