package pl.bookingsystem.webapp.client.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;


@Namespace("client/")
@ResultPath(value = "/")
public class DashboardAction extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> session;
    private Map<String, Object> application;
    private String username;
    private String password;


    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/client/dashboard.jsp")
    })
    public String execute() {
        return SUCCESS;
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

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }
}

