package pl.bookingsystem.webapp.user.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

@Namespace("/")
@ResultPath(value = "/")
public class UserDashboardAction extends ActionSupport {


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Action(value = "dashboard", results = {
            @Result(name = "success", location = "/modules/user/pages/dashboard.jsp")
    })
    public String execute() {

        return SUCCESS;

    }
}
