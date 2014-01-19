package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.AdditionDAO;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.UserDAO;
import pl.bookingsystem.db.dao.impl.AdditionDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.UserDAOImpl;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;

import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class AdditionAction extends ActionSupport implements SessionAware{

    private Map<String, Object> session;

    private String[][] data;
    public String[][] getData() {

        return data;
    }
    private String dataFrom;
    public String getDataFrom() {
        return dataFrom;
    }
    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }


    @Action(value = "additions-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                Hotel hotel = (Hotel) session.get("hotel");
                HotelDAO hotelManager = new HotelDAOImpl();
                List<Addition> additions = hotelManager.getAdditions(hotel.getId());

                int size = additions.size();
                data = new String[size][];
                for (int j = 0; j < additions.size(); j++) {

                    String[] tableS = new String[5];
                    Addition a = additions.get(j);
                    tableS[0] = String.valueOf(a.getId());
                    tableS[1] = String.valueOf(a.getName());
                    tableS[2] = String.valueOf(a.getDescription());
                    tableS[3] = String.valueOf(a.getPrice());
                    tableS[4] = String.valueOf(a.getPublished());

                    data[j] = tableS;
                }
                return SUCCESS;
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "additions-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String additionsAdd() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                AdditionDAO additionManager = new AdditionDAOImpl();
                JSONObject jsonObject = new JSONObject(dataFrom);
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                if ((description.isEmpty())) {
                    description = "-";
                }

//HOTEL
                Hotel hotel = (Hotel) session.get("hotel");

//CREATE NEW ADDITION
                Addition addition = new Addition(name, description, hotel);

//PRICE
                Double price = Double.parseDouble(jsonObject.getString("price"));
                addition.setPrice(price);

//PUBLISHED
                Boolean published = Boolean.parseBoolean(jsonObject.getString("published"));
                addition.setPublished(published);
                additionManager.save(addition);

//UPDATE SESSION
                if (hotel != null) {
                    HotelDAO hotelManager = new HotelDAOImpl();
                    Hotel hotel2 = hotelManager.selectByID(Hotel.class, hotel.getId());
                    session.put("hotel", hotel2);
                }

                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
