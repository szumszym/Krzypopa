package pl.bookingsystem.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

@Namespace("/")
@ResultPath(value = "/")
@Result(name = "success", type = "redirect", location = "/index.jsp")
public class IndexAction extends ActionSupport {

    public IndexAction() {
        super();
    }
}