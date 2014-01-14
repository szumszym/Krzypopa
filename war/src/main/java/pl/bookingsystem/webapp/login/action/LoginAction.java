package pl.bookingsystem.webapp.login.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.User;

import java.util.Map;


@Namespace("/")
@ResultPath(value = "/")
public class LoginAction extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> application;

    private String username;
    private String password;
    private Map<String, Object> session;

    @Action(value = "dashboard", results = {
            @Result(name = "adminlogged", location = "/modules/admin/dashboard.jsp"),
            @Result(name = "employeelogged", location = "/modules/employee/dashboard.jsp"),
            @Result(name = "ownerlogged", location = "/modules/owner/dashboard.jsp"),
            @Result(name = "clientlogged", location = "/modules/client/dashboard.jsp"),

            @Result(name = "error", location = "/modules/login/register_user.jsp")  //TODO: incorrect user or pass moze byc wyswietlany na tej samej stronie
    })
    public String checkCredentials() {

        UserDAO userManager = new UserDAOImpl();
        User user = userManager.checkRegisteredUser(username, password);
        if (user != null) {

            User.Type userType = user.getType();
            session.put("username", getUsername());
            session.put("user", user);
            //TODO: session.put("isClient", false);

            if (User.Type.ADMIN.equals(userType)) {
                return "adminlogged";
            } else if (User.Type.EMPLOYEE.equals(userType)) {
                return "employeelogged";
            } else if (User.Type.OWNER.equals(userType)) {
                return "ownerlogged";
            }
        } else {
            ClientDAO clientManager = new ClientDAOImpl();
            Client client = clientManager.checkRegisteredClient(username, password);
            if (client != null) {
                session.put("client", client);
                //TODO: session.put("isClient", true);
                return "clientlogged";
            }

        }
        return ERROR;
    }


    @Action(value = "logout", results = {
            @Result(name = "success", location = "/modules/login/logout.jsp")
    })
    public String logout() {
        setUsername("");
        setPassword("");
        session.put("username", "");
        session.put("user", null);
        session.put("client", null);
        return SUCCESS;
    }

    @Action(value = "login", results = {
            @Result(name = "success", location = "/modules/login/login.jsp")
    })
    public String goToLogin() {
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;

    }
}