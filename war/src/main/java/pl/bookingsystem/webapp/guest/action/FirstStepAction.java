package pl.bookingsystem.webapp.guest.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.exception.JDBCConnectionException;
import org.json.JSONObject;
import pl.bookingsystem.db.dao.ReservationDAO;
import pl.bookingsystem.db.dao.RoomDAO;
import pl.bookingsystem.db.dao.impl.ReservationDAOImpl;
import pl.bookingsystem.db.dao.impl.RoomDAOImpl;
import pl.bookingsystem.db.entity.Reservation;
import pl.bookingsystem.db.entity.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static pl.bookingsystem.webapp.action.Utils.isOverlapping;
import static pl.bookingsystem.webapp.action.Utils.setMsg;

@ParentPackage("json-default")
@Namespace("/")
@ResultPath(value = "/")
public class FirstStepAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    private String[][] data;

    public String[][] getData() {

        return data;
    }

    private RoomDAO roomDAO;
    private ReservationDAO reservationDAO;

    public FirstStepAction() {
        roomDAO = new RoomDAOImpl();
        reservationDAO = new ReservationDAOImpl();
    }

    public FirstStepAction(RoomDAO roomDAO, ReservationDAO reservationDAO) {
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
    }

    private String dataFrom;

    public String getDataFrom() {
        return dataFrom;
    }

    @Action(value = "first-page", results = {
            @Result(name = "success", type = "json"),
            @Result(name = "error", type = "json"),
            @Result(name = "connect_error", type = "redirect", location = "/503.html")
    })
    public String stepFirstPutDataToSession() {
        try {
            JSONObject jsonObject = new JSONObject(dataFrom);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFromString = jsonObject.getString("date_from");
            Date date_from = sdf.parse(dateFromString);
            String dateToString = jsonObject.getString("date_to");
            Date date_to = sdf.parse(dateToString);
            String city = jsonObject.getString("city");

            List<String[]> freeRoomsList = getFreeRooms(date_from, date_to, city);

            if (freeRoomsList.size() == 0) {
                data = setMsg("NO_ROOMS");
                return ERROR;
            } else {

                int size = freeRoomsList.size();
                String[][] availableRoomsArray = new String[size][];
                for (int j = 0; j < size; j++) {
                    availableRoomsArray[j] = freeRoomsList.get(j);
                }

                session.put("available_rooms", availableRoomsArray);
                session.put("dateFrom", dateFromString);
                session.put("dateTo", dateToString);

                data = setMsg(SUCCESS);
                return SUCCESS;
            }
        } catch (ParseException e) {
            data = setMsg("WRONG_DATE");
            return ERROR;
        } catch (JDBCConnectionException e) {
            return "connection_error";
        } catch (Exception e) {
            data = setMsg("ERROR!!!", e.getMessage());
            return ERROR;
        }

    }

    public List<String[]> getFreeRooms(Date date_from, Date date_to, String city) {
        List<Room> rooms = roomDAO.getRoomsFromCity(city);
        List<String[]> arrayList = new ArrayList<String[]>();

        for (Room room : rooms) {
            boolean isAvadaible = true;

            List<Reservation> reservations = reservationDAO.getAllReservationsFrom(room);
            for (Reservation res : reservations) {
                Date dateFrom2 = res.getDate_from();
                Date dateTo2 = res.getDate_to();
                if (isOverlapping(date_from, date_to, dateFrom2, dateTo2)) {
                    isAvadaible = false;
                }
            }
            if (isAvadaible) {
                String hotelname = room.getHotel().getName();

                String[] roomArray = new String[10];
                roomArray[0] = String.valueOf(room.getId());
                roomArray[1] = hotelname;
                roomArray[2] = String.valueOf(room.getNo_room());
                roomArray[3] = String.valueOf(room.getName());
                roomArray[4] = String.valueOf(room.getBed());
                roomArray[5] = String.valueOf(room.getCapacity());
                roomArray[6] = String.valueOf(room.getAdditions(3));
                String description = room.getDescription();
                roomArray[7] = String.valueOf(description != null ? description : "-");
                Double roomPrice = room.getPrice();
                Double priceAdditions = room.getPriceAdditions();
                if (priceAdditions == null) {
                    priceAdditions = 0.0;
                }
                roomArray[8] = String.valueOf(roomPrice + "+" + priceAdditions);
                roomArray[9] = String.valueOf(roomPrice + priceAdditions);

                arrayList.add(roomArray);
            }
        }
        return arrayList;
    }


    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void setReservationDAO(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }
}