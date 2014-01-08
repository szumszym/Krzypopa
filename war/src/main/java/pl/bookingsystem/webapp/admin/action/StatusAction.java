package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Status;

import java.util.List;


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
                tableS[3] = String.valueOf(s.getColor()); ////TODO można by dodać zamiast textu kolor do komurki jako bg

                //tableS[4] = "PUBLISH";

                if (s.getPublish()){
                    tableS[4] = "Published";
                }else{
                    tableS[4] = "Not Published";
                }

                data[j] = tableS;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }



    @Action(value = "status-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String statusAdd() {
        try {

            System.out.println(dataFrom);
            StatusDAO statusManager = new StatusDAOImpl();
            JSONObject jsonObject = new JSONObject(dataFrom);
            String type = (String) jsonObject.get("type");
            String color = (String) jsonObject.get("color");
            String description = "EMPTY";

            Boolean publish=true;

            if (((String) jsonObject.get("publish")).matches("on")){
                publish = true;
            }else{
                publish = false;
            }
            if (!(((String) jsonObject.get("description")).isEmpty())) {
                description = (String) jsonObject.get("description");
            }
            Status status = new Status(type, description);
            status.setColor(color);
            status.setPublish(publish);
            statusManager.save(status);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

}
