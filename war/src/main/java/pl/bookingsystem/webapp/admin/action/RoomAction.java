package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.*;

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

import java.util.Iterator;

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
                reservation[3] = "BED";
                reservation[4] = String.valueOf(r.getAdditions(3));
                //reservation[4] = "Additions";
                reservation[5] = String.valueOf(r.getDescription());
                reservation[6] = String.valueOf(r.getDescription());
                reservation[7] = "I";
                reservation[8] = "E";
                reservation[9] = "D";

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
    public String roomnAdd() {
        try {
            System.out.println(dataFrom);
            AdditionDAO additionManager =new AdditionDAOImpl();

            JSONObject jsonObject = new JSONObject(dataFrom);
            String room_name = (String) jsonObject.get("room_name");
            Integer roomno = Integer.parseInt( (String) jsonObject.get("roomno"));
            String description = (String) jsonObject.get("description");
            Integer capacity = Integer.parseInt( (String) jsonObject.get("capacity"));
            JSONArray additions = (JSONArray) jsonObject.get("addition");
            Set<Addition> additionSet=new HashSet<Addition>();
            List<String> addName =  new ArrayList<String>();
            List<Addition> additionList;
            for (int i=0; i<additions.length(); i++) {
                    addName.add(additions.getString(i));
                   log.error("Name: " + addName.get(i));

            }

            additionList = additionManager.getAdditionsBy(addName,"name");

            if (!additionList.isEmpty()){
            additionSet.addAll(additionList);}

            for (int i=0; i<additionList.size(); i++) {
                System.out.printf("[%d] addition: %s", i, additionList.get(i));
            }

            Long hotel_id =  Long.parseLong((String) jsonObject.get("hotel"));
            HotelDAO hotelManager = new HotelDAOImpl();
            Hotel hotel = hotelManager.selectByID(Hotel.class, hotel_id);

            RoomDAO roomManager = new RoomDAOImpl();
            Room room = new Room (roomno, room_name, "None", capacity,description, hotel, additionSet);
            for (Addition addition : additionList) {
                addition.addRoom(room);
                additionManager.save(addition);
            }

            roomManager.save(room);

            hotel.addRoom(room);
            hotelManager.save(hotel);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

}
