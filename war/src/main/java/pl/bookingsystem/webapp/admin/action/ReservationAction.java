package pl.bookingsystem.webapp.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.Client;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ParentPackage("json-default")
@Namespace("")
public class ReservationAction extends ActionSupport {
    private static final Logger log = Logger.getLogger(ReservationAction.class);
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


    @Action(value = "reservation-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {

            ReservationDAO reservationManager = new ReservationDAOImpl();
            List<Reservation> reservations = reservationManager.selectAll(Reservation.class);

            int size = reservations.size();
            data = new String[size][];
            for (int j = 0; j < reservations.size(); j++) {
                String[] reservation = new String[8];
                Reservation r = reservations.get(j);
                reservation[0] = String.valueOf(r.getId());
                reservation[1] = String.valueOf(r.getName());
                reservation[2] = String.valueOf(r.getDate_from());
                reservation[3] = String.valueOf(r.getDate_to());
                reservation[4] = String.valueOf(r.getPerson_count());
                reservation[5] = String.valueOf(r.getStatus());
                reservation[6] = String.valueOf(r.getEntry_date());
                reservation[7] = String.valueOf(r.getUpdate_date());

                data[j] = reservation;
            }

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }


    @Action(value = "reservation-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationAdd() {
        try {
            System.out.println(dataFrom);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = (String) jsonObject.get("name");
            Date date_from = simpleDateFormat.parse((String) jsonObject.get("date_from"));
            Date date_to = simpleDateFormat.parse((String) jsonObject.get("date_to"));
            Integer person_count = Integer.parseInt((String) jsonObject.get("person_count"));

            Long clientId = Long.parseLong((String) jsonObject.get("client_id"));
            Long statusId = Long.parseLong((String) jsonObject.get("status_id"));

            RoomDAO roomManager = new RoomDAOImpl();
            Room room;
            List<Room> roomList = new ArrayList<Room>();
            JSONArray roomIds = jsonObject.getJSONArray("room_ids");
            for (int i = 0; i < roomIds.length(); i++) {
                Long id = Long.valueOf((String) roomIds.get(i));
                room = roomManager.selectByID(Room.class, id);
                roomList.add(room);
            }

            StatusDAO statusManager = new StatusDAOImpl();
            Status status = statusManager.selectByID(Status.class, statusId);

            ClientDAO clientManager = new ClientDAOImpl();
            Client client = clientManager.selectByID(Client.class, clientId);

            Reservation reservation = new Reservation(name, date_from, date_to, person_count, client, status, roomList);
            ReservationDAO reservationManager = new ReservationDAOImpl();
            reservationManager.save(reservation);
            return SUCCESS;

        } catch (Exception e) {

            //  message = new ByteArrayInputStream("Data hasn't been saved".getBytes());
            return ERROR;
        }

    }

    @Action(value = "reservation-delete", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String delate() {
        try {

            ReservationDAO reservationManager = new ReservationDAOImpl();
            List<Reservation> reservations = reservationManager.selectAll(Reservation.class);



            return SUCCESS;

        } catch (Exception e) {

            return ERROR;
        }

    }


}



