package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.Hotel;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Status;
import pl.bookingsystem.db.entity.User;

import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;


@ParentPackage("json-default")
@Namespace("")
public class StatusAction extends ActionSupport implements SessionAware{

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


    @Action(value = "status-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDBsmall() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                Hotel hotel = (Hotel) session.get("hotel");
                StatusDAO statusDAO = new StatusDAOImpl();
                List<Status> statuses = statusDAO.getStatuses(hotel.getId());

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
            } else {
                data = setMsg("ERROR!!!", "You have no permission to execute this action!");
                return ERROR;
            }

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
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                StatusDAO statusDAO = new StatusDAOImpl();
                JSONObject jsonObject = new JSONObject(dataFrom);

                String type = jsonObject.getString("type");
                String color = jsonObject.getString("color");
                String description = jsonObject.getString("description");
                if ((description.isEmpty())) {
                    description = "-";
                }

//HOTEL
                Hotel hotel = (Hotel) session.get("hotel");

//CREATE NEW STATUS
                Status status = new Status(type, description, color, hotel);
                statusDAO.create(status);


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


    @Action(value = "status-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String statusDelete() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            StatusDAO statusDAO = new StatusDAOImpl();
            Status status = statusDAO.selectByID(Status.class, index);

            ReservationDAO reservationDAO = new ReservationDAOImpl();
            List<Reservation> reservations = reservationDAO.getAllReservationsWhichHas(status);

            int size = reservations != null ? reservations.size() : 0;
            if(size>0){
                data = setMsg("HAS_RESERVATIONS");
                return ERROR;
            } else {
                statusDAO.delete(status);
                data = setMsg(SUCCESS);
                return SUCCESS;
            }
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "status-update", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String statusUpdate() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = jsonObject.getLong("index");

                String type = jsonObject.getString("type");
                String color = jsonObject.getString("color");
                String description = jsonObject.getString("description");
                if ((description.isEmpty())) {
                    description = "-";
                }


//SET NEW PROPERTIES
                StatusDAO statusDAO = new StatusDAOImpl();
                Status status = statusDAO.selectByID(Status.class, index);
                status.setType(type);
                status.setColor(color);
                status.setDescription(description);

                statusDAO.update(status);

//UPDATE SESSION
                updateHotelsInSession();
                session.put("edit", null);

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

    @Action(value = "status-edit", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String statusEdit() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = Long.parseLong(jsonObject.getString("index"));

                StatusDAO statusDAO = new StatusDAOImpl();
                Status status = statusDAO.selectByID(Status.class, index);

                session.put("edit", status);

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
