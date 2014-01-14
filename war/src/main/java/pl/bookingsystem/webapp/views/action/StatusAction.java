package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.Status;

import java.util.List;

import static pl.bookingsystem.webapp.action.Utils.setMsg;


@ParentPackage("json-default")
@Namespace("")
public class StatusAction extends ActionSupport {

    private String[][] data;

    public String[][] getData() {

        return data;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    private String dataFrom;

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }


    @Action(value = "status-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            StatusDAO statusManager = new StatusDAOImpl();
            List<Status> statuses = statusManager.selectAll(Status.class);

            int size = statuses.size();
            data = new String[size][];
            for (int j = 0; j < statuses.size(); j++) {

                String[] tableS = new String[5];
                Status s = statuses.get(j);
                tableS[0] = String.valueOf(s.getId());
                tableS[1] = String.valueOf(s.getType());
                tableS[2] = String.valueOf(s.getDescription());
                tableS[3] = String.valueOf(s.getColor());

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "status-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String statusAdd() {
        try {

            StatusDAO statusManager = new StatusDAOImpl();
            JSONObject jsonObject = new JSONObject(dataFrom);

            String type = jsonObject.getString("type");
            String color = jsonObject.getString("color");
            String description = jsonObject.getString("description");
            if ((description.isEmpty())) {
                description = "-";
            }

            Status status = new Status(type, description, color);
            statusManager.save(status);

            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

}
