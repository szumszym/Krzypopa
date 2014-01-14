package pl.bookingsystem.webapp.views.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.bookingsystem.webapp.action.Utils.setMsg;

/**
 * Author: thx-
 * Date: 29.12.13 @  17:15
 */

@ParentPackage("json-default")
@Namespace("")
public class RoomAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(RoomAction.class);

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


    @Action(value = "room-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
          /*  HotelDAO hotelManager = new HotelDAOImpl();
            List<Room> rooms = hotelManager.getRooms(hotelID); //TODO: hotelId saved in session??
            */

            RoomDAO roomManager = new RoomDAOImpl();
            List<Room> rooms = roomManager.selectAll(Room.class);

            int size = rooms.size();
            data = new String[size][];
            for (int j = 0; j < rooms.size(); j++) {
                String[] room = new String[10];
                Room r = rooms.get(j);
                room[0] = String.valueOf(r.getId());
                room[1] = String.valueOf(r.getNo_room());
                room[2] = String.valueOf(r.getName());
                room[3] = String.valueOf(r.getBed());
                room[4] = String.valueOf(r.getAdditions(3));
                String description = r.getDescription();
                room[5] = String.valueOf(description != null ? description : "-");
                room[6] = String.valueOf(r.getPrice());
                room[7] = String.valueOf(r.getPublished());

                data[j] = room;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }


    @Action(value = "room-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String roomAdd() {
        try {
            AdditionDAO additionManager = new AdditionDAOImpl();

            JSONObject jsonObject = new JSONObject(dataFrom);
            String room_name = jsonObject.getString("room_name");
            Integer roomno = Integer.parseInt(jsonObject.getString("roomno"));
            String bed = "1x1";//TODO bed type e.g. "2x2" ...
            String description = jsonObject.getString("description");
            Integer capacity = Integer.parseInt(jsonObject.getString("capacity"));
            Double price = Double.parseDouble(jsonObject.getString("price"));
            Boolean published = Boolean.parseBoolean(jsonObject.getString("published"));

            JSONArray additionIds = (JSONArray) jsonObject.get("addition");
            Set<Addition> additionSet = new HashSet<Addition>();
            List<String> additionsIdsList = new ArrayList<String>();
            List<Addition> additionList;
            for (int i = 0; i < additionIds.length(); i++) {
                additionsIdsList.add(additionIds.getString(i));
                log.info("Name: " + additionsIdsList.get(i));
            }

            additionList = additionManager.getAdditionsBy(additionsIdsList, "id");
            for (String id : additionsIdsList) {
                additionList.add(additionManager.selectByID(Addition.class, Long.parseLong(id)));
            }

            if (!additionList.isEmpty()) {
                additionSet.addAll(additionList);
            }

            Long hotel_id = Long.parseLong(jsonObject.getString("hotel"));
            HotelDAO hotelManager = new HotelDAOImpl();
            Hotel hotel = hotelManager.selectByID(Hotel.class, hotel_id);

            RoomDAO roomManager = new RoomDAOImpl();

            Room room = new Room(roomno, room_name, bed, capacity, hotel, additionSet, price);
            room.setDescription(description);
            room.setPublished(published); //TODO: if employee (get from session) set always to false

            roomManager.save(room);
            hotelManager.addRoom(room, hotel);

            data = setMsg(SUCCESS);
            return SUCCESS;

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

            RoomDAO roomManager = new RoomDAOImpl();
            /*Room room = roomManager.selectByID(Room.class, index);
            if (room == null) {
                data = new String[][]{new String[]{"Room with index:" + index + " not found in DB!"}};
                return ERROR;
            }*/
            roomManager.deleteByID(index);
            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

}
