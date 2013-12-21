package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

@Namespace("/")
@ResultPath(value = "/")
public class AdminSettingsAction extends ActionSupport implements ApplicationAware, SessionAware {


    private Map<String, Object> session;
    private Map<String, Object> application;

    private String username;
    private String oldPassword;
    private String newPassword;

    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/admin/dashboard.jsp")
    })
    public String change() {
        if (oldPassword.equals(application.get("password"))) {
            session.put("username", username);
            session.put("password", newPassword);
            application.put("password", newPassword);
            //TODO: save to db new username and/or pass
            return SUCCESS;
        } else {
            return SUCCESS;
        }

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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }
}
