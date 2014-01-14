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
import pl.bookingsystem.db.dao.ClientDAO;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.StatusDAO;
import pl.bookingsystem.db.dao.impl.ClientDAOImpl;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.getClient;
import static pl.bookingsystem.webapp.action.Utils.getUser;
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

            ReservationDAO reservationManager = new ReservationDAOImpl();
            List reservations = null;// = reservationManager.selectAll(Reservation.class);

            User user = getUser(session);
            Client client = getClient(session);
            if (user != null) {
                reservations = reservationManager.getUserReservations(user);
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
                    Long roomNo = (Long) res[2];
                    Long hotelNo = (Long) res[3];

                    reservation[0] = String.valueOf(r.getId());
                    reservation[1] = String.valueOf(r.getName());
                    reservation[2] = String.valueOf(r.getDate_from());
                    reservation[3] = String.valueOf(r.getDate_to());
                    reservation[4] = String.valueOf(r.getPerson_count());
                    reservation[5] = String.valueOf(r.getStatus());
                    reservation[6] = String.valueOf(r.getEntry_date());
                    reservation[7] = String.valueOf(r.getUpdate_date() != null ? r.getUpdate_date() : "-");
                    reservation[8] = String.valueOf(r.getPrice());
                    reservation[9] = userEmail;
                    reservation[10] = String.valueOf(roomNo);
                    reservation[11] = String.valueOf(hotelNo);

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

            User currentUser = getUser(session);
            Client currentClient = getClient(session);

            System.out.println(dataFrom);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            JSONObject jsonObject = new JSONObject(dataFrom);
            String name = jsonObject.getString("name");
            Date date_from = simpleDateFormat.parse(jsonObject.getString("date_from"));
            Date date_to = simpleDateFormat.parse(jsonObject.getString("date_to"));
            Integer person_count = Integer.parseInt(jsonObject.getString("person_count"));


            JSONArray jsonRoomIdsArray = (JSONArray) jsonObject.get("room_ids");
            List<String> room_ids = convertJSONArrayToArrayList(jsonRoomIdsArray);
            RoomDAO roomManager = new RoomDAOImpl();
            List<Room> rooms = roomManager.selectByIDS(Room.class, room_ids);


            Client client = null;
            Long statusId = null;
            if (currentUser != null) {
                statusId = Long.parseLong(jsonObject.getString("status_id"));
                Long clientId = Long.parseLong(jsonObject.getString("client_id"));
                ClientDAO clientManager = new ClientDAOImpl();
                client = clientManager.selectByID(Client.class, clientId);
            } else if (currentClient != null) {
                client = currentClient;
                statusId = 1L;
            }

            StatusDAO statusManager = new StatusDAOImpl();
            Status status = statusManager.selectByID(Status.class, statusId);

            Double price = 0.0; //TODO wyliczyc cene: pokoje, dodatki itp..
            Reservation reservation = new Reservation(name, date_from, date_to, person_count, client, status, rooms, price);
            ReservationDAO reservationManager = new ReservationDAOImpl();
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



