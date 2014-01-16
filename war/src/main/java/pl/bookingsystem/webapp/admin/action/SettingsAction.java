package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

@Namespace("/")
@ResultPath(value = "/")
public class SettingsAction extends ActionSupport implements SessionAware {


    private Map<String, Object> session;

    private String username;
    private String oldPassword;
    private String newPassword;

    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/admin/dashboard.jsp")
    })
    public String change() {
        if (oldPassword.equals(session.get("password"))) {
            session.put("username", username);
            session.put("password", newPassword);
            session.put("password", newPassword);
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
}
