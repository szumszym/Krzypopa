package pl.bookingsystem.webapp.owner.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.entity.Hotel;

import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;


@ParentPackage("json-default")
@Namespace("/owner")
public class SelectHotelAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

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

    @Action(value = "select-hotel", results = {
            @Result(name = SUCCESS, type = "json"),
            @Result(name = ERROR, type = "json")
    })
    public String execute() {
        try {
            Hotel currentHotel = (Hotel) session.get("hotel");
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.valueOf(jsonObject.getString("index"));
            if (!index.equals(currentHotel.getId())) {
                HotelDAO hotelDAO = new HotelDAOImpl();
                Hotel hotel = hotelDAO.selectByID(index);
                session.put("hotel", hotel);
                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg(ERROR, "THE_SAME");
                return ERROR;
            }

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}

