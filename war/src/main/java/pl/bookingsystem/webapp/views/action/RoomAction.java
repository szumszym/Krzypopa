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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.convertJSONArrayToArrayList;
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
            if (hotel != null) {
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
                String description = jsonObject.getString("description");
                Integer capacity = Integer.parseInt(jsonObject.getString("capacity"));
                Double price = Double.parseDouble(jsonObject.getString("price"));

//BED
                String bedCountString = jsonObject.getString("bed_count");
                String bedTypeString = jsonObject.getString("bed_type");
                String bed = bedCountString + "x" + bedTypeString;


//ADDITIONS
                List<Addition> additionSet = getAdditionsFromJSON(jsonObject);

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

    @Action(value = "room-update", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String roomUpdate() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {
                User user = (User) session.get("user");
                User.Type type = user.getType();


                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = jsonObject.getLong("index");

                String room_name = jsonObject.getString("room_name");
                Integer roomno = Integer.parseInt(jsonObject.getString("roomno"));
                String description = jsonObject.getString("description");
                Integer capacity = Integer.parseInt(jsonObject.getString("capacity"));
                Double price = Double.parseDouble(jsonObject.getString("price"));

//BED
                String bedCountString = jsonObject.getString("bed_count");
                String bedTypeString = jsonObject.getString("bed_type");
                String bed = bedCountString + "x" + bedTypeString;


//ADDITIONS
                List<Addition> additionsList = getAdditionsFromJSON(jsonObject);

//HOTEL
                Hotel hotel = (Hotel) session.get("hotel");


//SET NEW PROPERTIES

                RoomDAO roomDAO = new RoomDAOImpl();
                Room room = roomDAO.selectByID(Room.class, index);
                room.setName(room_name);
                room.setNo_room(roomno);
                room.setDescription(description);
                room.setCapacity(capacity);
                room.setPrice(price);
                room.setBed(bed);
                room.setAdditions(additionsList);


//PUBLISH
                Boolean published = false;
                if (User.Type.ADMIN.equals(type) || User.Type.OWNER.equals(type)) {
                    published = Boolean.parseBoolean(jsonObject.getString("published"));
                }
                room.setPublished(published);

                roomDAO.update(room);

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
            if (size > 0) {
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


    @Action(value = "room-edit", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String roomEdit() {
        try {
            Boolean isUser = (Boolean) session.get("isUser");
            if (isUser) {

                JSONObject jsonObject = new JSONObject(dataFrom);
                Long index = Long.parseLong(jsonObject.getString("index"));

                RoomDAO roomDAO = new RoomDAOImpl();
                Room room = roomDAO.selectByID(Room.class, index);

                session.put("editRoom", room);

                Bed bed = new Bed(room);
                String bed_count = bed.getBed_count();
                String bed_type = bed.getBed_type();
                session.put("editRoom_bedCount", bed_count);
                session.put("editRoom_bedType", bed_type);

                String additions = generateAdditionsStringArray(room);
                session.put("editRoom_additions", additions);

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

    private List<Addition> getAdditionsFromJSON(JSONObject jsonObject) {
        if (!jsonObject.isNull("addition")) {
            JSONArray additionIds = (JSONArray) jsonObject.get("addition");
            List<Long> additionsIdsList = convertJSONArrayToArrayList(additionIds);

            AdditionDAO additionDAO = new AdditionDAOImpl();
            return additionDAO.selectByIDs(Addition.class, additionsIdsList);
        }
        return null;
    }

    private String generateAdditionsStringArray(Room room) {
        String additions = "";
        List<Addition> additionList = room.getAdditions();
        if (additionList.size() > 0) {
            for (Addition addition : additionList) {
                additions += addition.getId() + ", ";
            }
            additions = additions.substring(0, additions.length() - 2);
        }
        return additions;
    }
    private class Bed {
        private String bed_count;
        private String bed_type;

        public Bed(Room room) {
            String bed = room.getBed();
            int pos = bed.indexOf('x');
            bed_count = bed.substring(0, pos);
            bed_type = bed.substring(pos + 1, bed.length());
        }

        public String getBed_count() {
            return bed_count;
        }

        public String getBed_type() {
            return bed_type;
        }
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }


}
