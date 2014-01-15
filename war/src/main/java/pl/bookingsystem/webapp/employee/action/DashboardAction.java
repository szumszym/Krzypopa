package pl.bookingsystem.webapp.employee.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;


@Namespace("employee/")
@ResultPath(value = "/")
public class DashboardAction extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> session;

    private String username;
    private String password;
    private Map<String, Object> application;

    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/employee/dashboard.jsp")
    })
    public String execute() {
       /* User user = (User) session.get("user");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Set<Hotel> hotels = user.getHotels();
        System.out.println(hotels.size());
        session.clear();*/
        return SUCCESS;
    }

    //TODO: blad?
/*
    @Action(value = "settings", results = {
            @Result(name = "success", location = "/modules/admin/pages/settings.jsp")
    })
    public String goToSettings() {
        return SUCCESS;
    }*/

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

