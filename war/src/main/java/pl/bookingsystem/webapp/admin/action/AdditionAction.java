package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.AdditionDAO;
import pl.bookingsystem.db.dao.AddressDAO;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.AdditionDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Address;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.User;

import java.util.List;

@ParentPackage("json-default")
@Namespace("")
public class AdditionAction extends ActionSupport{

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


    @Action(value = "additions-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            AdditionDAO additionManager =new AdditionDAOImpl();
            List<Addition> additions = additionManager.selectAll(Addition.class);
            int size = additions.size();
            data = new String[size][];
            for (int j = 0; j < additions.size(); j++) {
                
                String[] tableS = new String[5];
                Addition a = additions.get(j);
                tableS[0] = String.valueOf(a.getId());
                tableS[1] = String.valueOf(a.getName());
                tableS[2] = String.valueOf(a.getDescription());
                tableS[3] = String.valueOf(a.getPirce());
//                tableS[4] = String.valueOf(a.getPublish());

                if (a.getPublish()){
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

    @Action(value = "additions-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String additionsAdd() {
        try {
            AdditionDAO additionManager = new AdditionDAOImpl();
            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = (String) jsonObject.get("name");
            Double price = Double.parseDouble((String) jsonObject.get("price"));
            String description="EMPTY";
            if(!(((String) jsonObject.get("description")).isEmpty())){
            description = (String) jsonObject.get("description");
            }
            System.out.println(dataFrom);

            Boolean publish=true;

            if (((String) jsonObject.get("publish")).matches("on")){
                publish = true;
            }else{
                publish = false;
            }

            Addition addition= new Addition(name, description);
            addition.setPirce(price);
            addition.setPublish(publish);
            additionManager.save(addition);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

}
