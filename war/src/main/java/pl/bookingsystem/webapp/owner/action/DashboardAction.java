package pl.bookingsystem.webapp.owner.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import pl.bookingsystem.db.entity.Hotel;

import java.util.Map;


@Namespace("owner/")
@ResultPath(value = "/")
public class DashboardAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private String username;
    private String password;


    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/owner/dashboard.jsp"),
            @Result(name = "newowner", location = "/modules/owner/dashboard-new.jsp")
    })
    public String execute() {
         Hotel hotel = (Hotel) session.get("hotel");
        if(hotel!=null){
            return SUCCESS;
        } else {
            return "newowner";
        }
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
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}

