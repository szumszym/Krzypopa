package pl.bookingsystem.webapp.admin.action;

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
                String[] reservation = new String[10];
                Room r = rooms.get(j);
                reservation[0] = String.valueOf(r.getId());
                reservation[1] = String.valueOf(r.getNo_room());
                reservation[2] = String.valueOf(r.getName());
                reservation[3] = String.valueOf(r.getPriceAndAdditions());
                reservation[4] = String.valueOf(r.getAdditions(4));  // liczba pozwala ograniczyć wyświetlaną liczbe dodatków
                reservation[5] = String.valueOf(r.getDescription());

                data[j] = reservation;
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
            String room_name = (String) jsonObject.get("room_name");
            Integer roomno = Integer.parseInt((String) jsonObject.get("roomno"));
            String description = (String) jsonObject.get("description");
            Integer capacity = Integer.parseInt((String) jsonObject.get("capacity"));
            Double price = Double.parseDouble((String) jsonObject.get("price"));
            JSONArray additions = (JSONArray) jsonObject.get("addition");
            Set<Addition> additionSet = new HashSet<Addition>();
            List<String> addName = new ArrayList<String>();
            List<Addition> additionList;
            for (int i = 0; i < additions.length(); i++) {
                addName.add(additions.getString(i));
                log.info("Name: " + addName.get(i));

            }

            additionList = additionManager.getAdditionsBy(addName, "id");
            for (String id : addName){
                additionList.add(additionManager.selectByID(Addition.class, Long.parseLong(id)));

            }

            if (!additionList.isEmpty()) {
                additionSet.addAll(additionList);
            }

            Long hotel_id = Long.parseLong((String) jsonObject.get("hotel"));
            HotelDAO hotelManager = new HotelDAOImpl();
            Hotel hotel = hotelManager.selectByID(Hotel.class, hotel_id);

            RoomDAO roomManager = new RoomDAOImpl();
            Room room = new Room(roomno, room_name, "None", capacity, description, hotel, additionSet);
            room.setPrice(price);
            room.setAdditions(additionSet);
            room.setHotel(hotel);
/*            for (Addition addition : additionList) {
                addition.addRoom(room);
                additionManager.save(addition);
            }*/
            roomManager.save(room);

            /*hotel.addRoom(room);
            hotelManager.save(hotel);*/
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
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
            Long index = Long.parseLong((String) jsonObject.get("index"));

            RoomDAO roomManager = new RoomDAOImpl();
            /*Room room = roomManager.selectByID(Room.class, index);
            if (room == null) {
                data = new String[][]{new String[]{"Room with index:" + index + " not found in DB!"}};
                return ERROR;
            }*/
            roomManager.deleteByID("Room", index);
            data = new String[][]{new String[]{SUCCESS}};
            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{e.getMessage()}};
            return ERROR;
        }

    }

}
