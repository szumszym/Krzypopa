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
            @Result(name = SUCCESS, type = "json")
    })
    public String execute() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            String index = jsonObject.getString("index");
            HotelDAO hotelManager = new HotelDAOImpl();
            Hotel hotel = hotelManager.selectByID(index);
            session.put("hotel", hotel);
            //TODO: dodac JSON action
            //TODO: get hotel from DB by id
            //TODO: save hotel to session

            //TODO: gdy strona sie odswiezy - trzeba ustawic wykrywanie hotelu w akcjach typu getData-xxx
            //TODO: !!!!!! albo ustawic akcje zeby przeladowala caly dashboard jeszcze raz - wtedy nie trzeba odswiezac htmla
            data = setMsg(SUCCESS);
            return SUCCESS;
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

