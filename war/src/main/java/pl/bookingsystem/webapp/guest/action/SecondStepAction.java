package pl.bookingsystem.webapp.guest.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.dao.impl.StatusDAOImpl;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;
import pl.bookingsystem.db.entity.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.*;

@ParentPackage("json-default")
@Namespace("/")
public class SecondStepAction extends ActionSupport implements SessionAware {

    private String[][] data;
    private Map<String, Object> session;

    public String[][] getData() {

        return data;
    }

    private StatusDAOImpl statusDAO;

    private ReservationDAO reservationDAO;

    private RoomDAO roomDAO;

    public SecondStepAction() {
        statusDAO = new StatusDAOImpl();
        reservationDAO = new ReservationDAOImpl();
        roomDAO = new RoomDAOImpl();
    }


    public String getDataFrom() {
        return dataFrom;
    }

    private String dataFrom;

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    @Action(value = "secondStep-get-rooms", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String stepSecondGetAvailableRooms() {
        try {
            String[][] availableRoomsArray = (String[][]) session.get("available_rooms");

            int size = availableRoomsArray.length;
            data = new String[size][];
            System.arraycopy(availableRoomsArray, 0, data, 0, size);

            return SUCCESS;

        } catch (Exception e) {
            data = new String[][]{new String[]{"ERROR!!!"}};
            return ERROR;
        }

    }

    @Action(value = "secondStep", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json")
    })
    public String secondStep() throws ParseException {

        JSONObject jsonObject = new JSONObject(dataFrom);
        String name = jsonObject.getString("name");
        Integer person_count = Integer.parseInt(jsonObject.getString("person_count"));
        Double price = Double.valueOf(jsonObject.getString("price"));

        //DATES
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date_from = sdf.parse(jsonObject.getString("date_from"));
        Date date_to = sdf.parse(jsonObject.getString("date_to"));
//ROOMS
        JSONArray jsonRoomIdsArray = (JSONArray) jsonObject.get("room_ids");
        List<Long> room_ids = convertJSONArrayToArrayList(jsonRoomIdsArray);
        List<Room> rooms = roomDAO.selectByIDs(Room.class, room_ids);

        //CHECK IF TERM IS AVADAIBLE
        for (Room room : rooms) {
            List<Reservation> reservations = reservationDAO.getAllReservationsFrom(room);
            for (Reservation res : reservations) {
                Date dateFrom2 = res.getDate_from();
                Date dateTo2 = res.getDate_to();
                if (isOverlapping(date_from, date_to, dateFrom2, dateTo2)) {

                    String date1 = sdf.format(dateFrom2);
                    String date2 = sdf.format(dateTo2);
                    data = setMsg("overlapped", "'" + room.getName() + "' is already reserved between " + date1 + " and " + date2);
                    return ERROR;
                }
            }
        }

//STATUS
        Status status = statusDAO.selectByID(Status.class, 1L);
        // Status status = new Status("Nowa", "Nowa rezerwacja od klienta", "#000000");

//CREATE NEW RESERVATION
        Reservation reservation = new Reservation(name, date_from, date_to, person_count, status, rooms, price);
        reservation.setEntry_date(new Date());
        session.put("reservation", reservation);


        data = setMsg(SUCCESS);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}