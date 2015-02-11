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
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.AdditionDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.Addition;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.User;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class AdditionAction extends ActionSupport implements SessionAware {

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
                AdditionDAO additionDAO = new AdditionDAOImpl();
                List<Addition> additions = additionDAO.getAdditions(hotel);

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

                AdditionDAO additionDAO = new AdditionDAOImpl();
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
                additionDAO.create(addition);

//UPDATE SESSION
                updateHotelsInSession();
                session.put("edit", null);

                data = setMsg(SUCCESS);
                return SUCCESS;
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

        } catch (OptimisticLockException o){
            data = setMsg("ALREADY_MODIFIED");
            return SUCCESS;
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "additions-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String additionDelete() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            AdditionDAO additionDAO = new AdditionDAOImpl();
            Addition addition = additionDAO.selectByID(Addition.class, index);

            RoomDAO roomDAO = new RoomDAOImpl();
            List<Room> rooms = roomDAO.getAllRoomsWhichHave(addition);

            int size = rooms != null ? rooms.size() : 0;
            if (size > 0) {
                data = setMsg("HAS_ROOMS");
                return ERROR;
            } else {
                additionDAO.delete(addition);
                data = setMsg(SUCCESS);
                return SUCCESS;
            }
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    @Action(value = "additions-update", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String additionUpdate() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = jsonObject.getLong("index");

                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                if ((description.isEmpty())) {
                    description = "-";
                }
                Double price = Double.parseDouble(jsonObject.getString("price"));
                Boolean published = Boolean.parseBoolean(jsonObject.getString("published"));


                AdditionDAO additionDAO = new AdditionDAOImpl();
                Addition addition = additionDAO.selectByID(Addition.class, index);

//SET NEW PROPERTIES
                addition.setName(name);
                addition.setDescription(description);
                addition.setPrice(price);
                addition.setPublished(published);

                additionDAO.update(addition);

//UPDATE SESSION
                updateHotelsInSession();

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

    @Action(value = "additions-edit", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String additionEdit() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = Long.parseLong(jsonObject.getString("index"));

                AdditionDAO additionDAO = new AdditionDAOImpl();
                Addition addition = additionDAO.selectByID(Addition.class, index);

                session.put("edit", addition);

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

    private void updateHotelsInSession() {
        Hotel hotel = (Hotel) session.get("hotel");
        if (hotel != null) {
            HotelDAO hotelDAO = new HotelDAOImpl();
            Hotel updatedHotel = hotelDAO.selectByID(hotel.getId());
            session.put("hotel", updatedHotel);

            List<Hotel> sessionHotels;
            User currentUser = (User) session.get("user");
            Boolean isAdmin = (Boolean) session.get("isAdmin");
            if (isAdmin) {
                sessionHotels = hotelDAO.selectAllHotels();
            } else {
                sessionHotels = hotelDAO.selectAllHotelsOfUser(currentUser.getId());
            }
            session.put("hotels", sessionHotels);
        }
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }
}
