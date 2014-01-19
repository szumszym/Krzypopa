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
import pl.bookingsystem.db.dao.*;
import pl.bookingsystem.db.dao.impl.*;
import pl.bookingsystem.db.entity.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static pl.bookingsystem.webapp.action.Utils.daysBetween;
import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("")
public class ReservationAction extends ActionSupport implements SessionAware {
    private static final Logger log = Logger.getLogger(ReservationAction.class);
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


    @Action(value = "reservation-getData", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String dataFromDB() {
        try {
            Hotel hotel = (Hotel) session.get("hotel");
            Boolean isAdmin = (Boolean) session.get("isAdmin");

            ReservationDAO reservationManager = new ReservationDAOImpl();
            List reservations = null;

            User user = (User) session.get("user");
            Client client = (Client) session.get("client");
            if (user != null) {
                if(isAdmin){
                    reservations = reservationManager.getAllReservations();
                } else {
                    reservations = reservationManager.getHotelReservations(hotel);
                }
            } else if (client != null) {
                reservations = reservationManager.getClientReservations(client);
            }

            int size = reservations != null ? reservations.size() : 0;
            if (size > 0) {
                data = new String[size][];

                for (int j = 0; j < size; j++) {
                    String[] reservation = new String[12];
                    Object[] res = (Object[]) reservations.get(j);
                    Reservation r = (Reservation) res[0];
                    String userEmail = (String) res[1];
                    Integer roomNo = (Integer) res[2];
                    Long hotelNo = (Long) res[3];

                    HotelDAO hotelManager = new HotelDAOImpl();
                    Hotel hotel2 = hotelManager.selectByID(hotelNo);
                    String hotelName = hotel2.getName();

                    reservation[0] = String.valueOf(r.getId());
                    reservation[1] = String.valueOf(r.getName());
                    reservation[2] = String.valueOf(r.getDate_from());
                    reservation[3] = String.valueOf(r.getDate_to());
                    reservation[4] = String.valueOf(r.getPerson_count());
                    Status status = r.getStatus();
                    reservation[5] = String.valueOf(status.getColor()+"&"+status.getType());
                    reservation[6] = String.valueOf(r.getEntry_date() != null ? r.getUpdate_date() : "-");
                    reservation[7] = String.valueOf(r.getUpdate_date() != null ? r.getUpdate_date() : "-");
                    reservation[8] = String.valueOf(r.getPrice());
                    reservation[9] = userEmail;
                    reservation[10] = hotelName;
                    reservation[11] = String.valueOf(roomNo);

                        data[j] = reservation;
                }
            } else {
                data = null;
            }
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }


    @Action(value = "reservation-add", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String reservationAdd() {
        try {

            User currentUser = (User) session.get("user");
            Client currentClient = (Client) session.get("client");
            Hotel hotel = (Hotel) session.get("hotel");

            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = jsonObject.getString("name");
            Integer person_count = Integer.parseInt(jsonObject.getString("person_count"));

 //DATES
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date_from = simpleDateFormat.parse(jsonObject.getString("date_from"));
            Date date_to = simpleDateFormat.parse(jsonObject.getString("date_to"));

//ROOMS
            JSONArray jsonRoomIdsArray = (JSONArray) jsonObject.get("room_ids");
            List<String> room_ids = convertJSONArrayToArrayList(jsonRoomIdsArray);
            RoomDAO roomManager = new RoomDAOImpl();
            List<Room> rooms = roomManager.selectByIDS(Room.class, room_ids);

 //CHECK IF TERM IS AVADAIBLE
            ReservationDAO reservationManager = new ReservationDAOImpl();
            for (Room room : rooms) {
                List<Reservation> reservations = reservationManager.getAllReservationsFrom(hotel, room);
                for (Reservation res : reservations) {
                    res.getDate_from();
                    res.getDate_to();
                    //TODO: METODA!!!! - porownac czy nie zachodzi to na przedzial datefrom i dateto z formularza
                    if (true/*zachodzi*/) {
                        //TODO: setMsg zachodzi z inna rezerwacja
                        //return CONFLICT ?
                    }
                }
            }

//CLIENT AND STATUS
            Status status = null;
            Client client = null;
            if (currentUser != null) {
                Long statusId = Long.parseLong(jsonObject.getString("status_id"));
                StatusDAO statusManager = new StatusDAOImpl();
                status = statusManager.selectByID(Status.class, statusId);

                Long clientId = Long.parseLong(jsonObject.getString("client_id"));
                ClientDAO clientManager = new ClientDAOImpl();
                client = clientManager.selectByID(Client.class, clientId);
            } else if (currentClient != null) {
                client = currentClient;
                HotelDAO hotelManager = new HotelDAOImpl();

                List<Status> statuses = hotelManager.getStatuses(hotel.getId());
                status = statuses.get(0);
            }

//PRICE
            Double price = 0.0;
            for (Room room : rooms) {
                Set<Addition> additions = room.getAdditions();
                for (Addition add : additions) {
                    price += add.getPrice();
                }
                price += room.getPrice();
            }
            int days = daysBetween(date_from, date_to);
            price *= days;

 //CREATE NEW RESERVATION
            Reservation reservation = new Reservation(name, date_from, date_to, person_count, client, status, rooms, price);
            reservationManager.save(reservation);

            data = setMsg(SUCCESS);
            return SUCCESS;

        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    private List<String> convertJSONArrayToArrayList(JSONArray jsonArray) {
        List<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}



