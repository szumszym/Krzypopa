package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.AdditionDAO;
import pl.bookingsystem.db.dao.HotelDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.AdditionDAOImpl;
import pl.bookingsystem.db.dao.impl.HotelDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

/**
 * Author: thx-
 * Date: 29.12.13 @  17:15
 */

@ParentPackage("json-default")
@Namespace("")
public class RoomAction extends ActionSupport implements SessionAware {
    private static final Logger log = Logger.getLogger(RoomAction.class);

    private String[][] data;
    private Map<String, Object> session;

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


    @Action(value = "room-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            Hotel hotel = (Hotel) session.get("hotel");
            RoomDAO roomDAO = new RoomDAOImpl();
            Long hotelId;
            if(hotel!=null){
                  hotelId = hotel.getId();
            } else {
                JSONObject jsonObject = new JSONObject(dataFrom);
                hotelId = Long.valueOf(jsonObject.getString("index"));
            }
            List<Room> rooms = roomDAO.getRooms(hotelId);
            int size = rooms.size();
            data = new String[size][];
            for (int j = 0; j < rooms.size(); j++) {
                String[] room = new String[9];
                Room r = rooms.get(j);
                room[0] = String.valueOf(r.getId());
                room[1] = String.valueOf(r.getNo_room());
                room[2] = String.valueOf(r.getName());
                room[3] = String.valueOf(r.getBed());
                room[4] = String.valueOf(r.getCapacity());
                room[5] = String.valueOf(r.getAdditions(3));
                String description = r.getDescription();
                room[6] = String.valueOf(description != null ? description : "-");
                room[7] = String.valueOf(r.getPrice());
                room[8] = String.valueOf(r.getPublished());

                data[j] = room;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!", e.getMessage()}};
            return ERROR;
        }

    }


    @Action(value = "room-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String roomAdd() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {
                User user = (User) session.get("user");
                User.Type type = user.getType();


                JSONObject jsonObject = new JSONObject(dataFrom);
                String room_name = jsonObject.getString("room_name");
                Integer roomno = Integer.parseInt(jsonObject.getString("roomno"));

                String bedCountString = jsonObject.getString("bed_count");
                Integer bed_count = Integer.parseInt(bedCountString);
                String bedTypeString = jsonObject.getString("bed_type");
                Integer bed_type = Integer.parseInt(bedTypeString);
                String bed = bedCountString + "x" + bedTypeString;

                String description = jsonObject.getString("description");
                Integer capacity = Integer.parseInt(jsonObject.getString("capacity"));
                Double price = Double.parseDouble(jsonObject.getString("price"));

//ADDITIONS

                List<Addition> additionSet = new LinkedList<Addition>();
                if (!jsonObject.isNull("addition")) {
                    JSONArray additionIds = (JSONArray) jsonObject.get("addition");
                    List<Long> additionsIdsList = new ArrayList<Long>();
                    List<Addition> additionList;
                    for (int i = 0; i < additionIds.length(); i++) {
                        additionsIdsList.add(additionIds.getLong(i));
                        log.info("Name: " + additionsIdsList.get(i));
                    }
                    AdditionDAO additionDAO = new AdditionDAOImpl();
                    additionList = additionDAO.selectByIDs(Addition.class, additionsIdsList);
                   /* for (Long id : additionsIdsList) {
                        additionList.add(additionDAO.selectByID(Addition.class, id));
                    }*/

                    if (!additionList.isEmpty()) {
                        additionSet.addAll(additionList);
                    }
                }

//HOTEL
                Hotel hotel = (Hotel) session.get("hotel");


//CREATE NEW ROOM AND SAVE
                RoomDAO roomDAO = new RoomDAOImpl();
                Room room = new Room(roomno, room_name, bed, capacity, hotel, additionSet, price);
                room.setDescription(description);


//PUBLISH
                Boolean published = false;
                if (User.Type.OWNER.equals(type) || User.Type.OWNER.equals(type)) {
                    published = Boolean.parseBoolean(jsonObject.getString("published"));
                }
                room.setPublished(published);

                roomDAO.create(room);

//UPDATE SESSION
                if (hotel != null) {
                    HotelDAO hotelDAO = new HotelDAOImpl();
                    Hotel hotel2 = hotelDAO.selectByID(hotel.getId());
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

    @Action(value = "room-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String roomDelete() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);
            Long index = Long.parseLong(jsonObject.getString("index"));

            RoomDAO roomDAO = new RoomDAOImpl();
            Room room = roomDAO.selectByID(Room.class, index);

            ReservationDAO reservationDAO = new ReservationDAOImpl();
            List<Reservation> reservations = reservationDAO.getAllReservationsFrom(room);
            int size = reservations != null ? reservations.size() : 0;
            if(size>0){
                data = setMsg("HAS_RESERVATIONS");
                return ERROR;
            } else {
                roomDAO.deleteByID(index);
                data = setMsg(SUCCESS);
                return SUCCESS;
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
